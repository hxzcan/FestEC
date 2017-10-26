package com.hx.latte.app.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.hx.latte.R;
import com.hx.latte.app.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by hx on 2017/9/5 0005.
 * email:362970502@qq.com
 * des:进度条
 */

public class LatteLoader {
    private static final int LOADER_SIZE_SCALE=8;
    private static final int LOADER_OFFSET_SCALE=10;//偏移量
    //集合统一管理进度条
    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();
    //默认的进度条样式
    private static final String DEFAULT_LOADING= LoaderStyles.BallClipRotateIndicator.name();
    /**
     * 显示进度条
     * @param context 显示进度条的界面
     * @param type 进度条类型
     */
    public static void showLoading(Context context,String type){

        //尽量使用v7包中的，可以提高兼容性，在style资源文件中定义dialog的样式
        final AppCompatDialog dialog=new AppCompatDialog(context,R.style.loading_dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView= LoaderCreator.create(type,context);
        //把进度条布局加入dialog
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth= DimenUtil.getScreenWidth();
        int deviceHeight=DimenUtil.getScreenHeight();

        Window dialogWindow=dialog.getWindow();
        if (dialogWindow!=null){
            WindowManager.LayoutParams layoutParams=dialogWindow.getAttributes();
            //宽高缩放8倍
            layoutParams.width=deviceWidth/LOADER_SIZE_SCALE;
            layoutParams.height=deviceHeight/LOADER_SIZE_SCALE;
            layoutParams.height=layoutParams.height+LOADER_OFFSET_SCALE;
            //设置居中
            layoutParams.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 默认的加载进度条样式
     * @param context
     */
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADING);
    }

    /**
     *
     * @param context 上下文
     * @param type 枚举类型里面的内容
     */
    public static void showLoading(Context context,Enum<LoaderStyles> type){
        showLoading(context,type.name());
    }
    /**
     * 停止加载进度条
     */
    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS) {
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();//取消，会停止里面的回调一些的方法
                    //dialog.dismiss();//只是让进度条消失
                }
            }
        }
    }
}
