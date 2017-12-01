package com.hx.latte.main.personal.bean;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hx.latte.app.delegate.LatteDelegate;


/**
 * 设置的可以灵活添加的设置
 * Created by hexiao on 2017/11/29.
 */

public class SetItemListBean implements MultiItemEntity {
    private Integer mItemType;
    private String mImageUrl;
    private String mText;
    private String mValue;
    private LatteDelegate mLatteDelegate;
    private Integer mId;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener=null;

    public SetItemListBean(Integer mItemType, String mImageUrl, String mText, String mValue,
                           LatteDelegate mLatteDelegate, Integer mId, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mLatteDelegate = mLatteDelegate;
        this.mId = mId;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmText() {
        return mText;
    }

    public String getmValue() {
        return mValue;
    }

    public LatteDelegate getmLatteDelegate() {
        return mLatteDelegate;
    }

    public Integer getmId() {
        return mId;
    }

    public CompoundButton.OnCheckedChangeListener getmOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public static class Builder{
        private Integer mItemType;
        private String mImageUrl;
        private String mText;
        private String mValue;
        private LatteDelegate mLatteDelegate;
        private Integer mId;
        private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener=null;

        public Builder setmItemType(Integer mItemType) {
            this.mItemType = mItemType;
            return this;
        }

        public Builder setmImageUrl(String mImageUrl) {
            this.mImageUrl = mImageUrl;
            return this;
        }

        public Builder setmText(String mText) {
            this.mText = mText;
            return this;
        }

        public Builder setmValue(String mValue) {
            this.mValue = mValue;
            return this;
        }

        public Builder setmLatteDelegate(LatteDelegate mLatteDelegate) {
            this.mLatteDelegate = mLatteDelegate;
            return this;
        }

        public Builder setmId(Integer mId) {
            this.mId = mId;
            return this;
        }

        public Builder setmOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
            this.mOnCheckedChangeListener = mOnCheckedChangeListener;
            return this;
        }

        public  SetItemListBean build(){
            return new SetItemListBean(mItemType,mImageUrl,mText,mValue,mLatteDelegate,mId,mOnCheckedChangeListener);
        }
    }
}
