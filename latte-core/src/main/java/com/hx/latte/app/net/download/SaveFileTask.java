package com.hx.latte.app.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.hx.latte.app.Latte;
import com.hx.latte.app.net.callback.IRequest;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by hx on 2017/9/11 0011.
 * email:362970502@qq.com
 * des:异步处理下载文件
 */

/**
 * Object 第一个参数是传进来的参数，如果是不用的类型可以写Object；如果是统一的结果类型可以写为具体的
 * Void 后台执行的任务进度
 * File 是后台计算的结果类型
 */
public class SaveFileTask extends AsyncTask<Object,Void,File>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESSFUL;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESSFUL) {
        this.REQUEST = REQUEST;
        this.SUCCESSFUL = SUCCESSFUL;
    }

    /**
     * 在子线程中的操作,不能更改UI界面
     * @param  objects 是传过来的参数 ，有几个获取几个
     * @return
     */
    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir= (String) objects[0];
        String extension= (String) objects[1];
        String name= (String) objects[2];
        ResponseBody responseBody= (ResponseBody) objects[3];
        InputStream inputStream=responseBody.byteStream();
        if (downloadDir==null||downloadDir.equals("")){
            downloadDir="download_dir";
        }
        if (extension==null||extension.equals("")){
            extension="";
        }
        if (name==null){
           return  FileUtil.writeToDisk(inputStream,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(inputStream, downloadDir, name);
        }
    }

    /**
     * 在主线程中的操作
     * @param file
     * onPostExecute 在后台任务结束时会被调用，计算结果作为参数传递到此方法中，直接将结果显示到UI组件中
     */
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESSFUL!=null){
            //获取下载的文件路径
            SUCCESSFUL.onSuccessful(file.getPath());
        }
        //结束
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    /**
     * 自动安装apk
     * @param file 存放apk的文件路径
     */
    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install=new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getApplication().startActivity(install);
        }
    }

    /**
     * 直接将进度信息更新到UI组件
     * @param values
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
