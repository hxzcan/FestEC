package com.hx.latte.app.utils;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by hx on 2017/10/11 0011.
 * email:362970502@qq.com
 * des:处理键盘收回事件
 */

public class InputUtil {

    public static boolean isShouldHideInput(View view, MotionEvent motionEvent){
        if (view!=null&&(view instanceof EditText)){
            int[] leftTop={0,0};
            //获取输出框当前的位置
            view.getLocationInWindow(leftTop);
            int left=leftTop[0];
            int top=leftTop[1];
            int bottom=top+view.getHeight();
            int right=left+view.getWidth();
            if (motionEvent.getX()>left&&motionEvent.getX()<right&&motionEvent.getY()>top&&motionEvent.getY()<bottom){
                //点击是editText范围内保留editText状态
                return  false;
            }else{
                return  true;
            }
        }
        return false;
    }
}
