package com.hx.latte.app.delegate.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hx.latte.app.delegate.LatteDelegate;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:显示的fragment内容
 */

public abstract class BottomItemDelegate extends LatteDelegate  implements View.OnKeyListener{
    private long mEixtTime=0L;
    private static final float EXIT_TIME=2000L;

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction()==KeyEvent.ACTION_DOWN&&keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis()-mEixtTime>EXIT_TIME){
                Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show();
                mEixtTime=System.currentTimeMillis();
            }else {
                _mActivity.finish();
                if (mEixtTime!=0){
                    mEixtTime=0;
                }
            }
            return true;//返回true已经把事件消费
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        final View rootView=getView();
        if (rootView!=null){
            rootView.setFocusable(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }
}
