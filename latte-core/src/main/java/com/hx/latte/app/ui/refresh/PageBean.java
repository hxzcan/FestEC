package com.hx.latte.app.ui.refresh;

/**
 * Created by hexiao on 2017/11/7.
 * 分页的bean
 */

public class PageBean {
    //当前页
    private int mPageIndex=0;
    //总数局数
    private int mTotal=0;
    //每页显示的数据条数
    private int mPageSize=10;
    //当前已经显示了几条数据
    private int mCurrentTotalNumber=0;

    private int mDelay=0;

    public int getmPageIndex() {
        return mPageIndex;
    }

    public PageBean setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmTotal() {
        return mTotal;
    }

    public PageBean setmTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    public PageBean setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getmCurrentTotalNumber() {
        return mCurrentTotalNumber;
    }

    public PageBean setmCurrentTotalNumber(int mCurrentTotalNumber) {
        this.mCurrentTotalNumber = mCurrentTotalNumber;
        return this;
    }

    public int getmDelay() {
        return mDelay;
    }

    public PageBean setmDelay(int mDelay) {
        this.mDelay = mDelay;
        return this;
    }

    //页数加1
    PageBean addInex(){
        mPageIndex++;
        return this;
    }
}
