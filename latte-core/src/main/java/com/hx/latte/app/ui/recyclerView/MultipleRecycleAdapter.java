package com.hx.latte.app.ui.recyclerView;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hx.latte.R;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.ui.banners.BannerCreator;
import com.hx.latte.app.ui.glide.GlideApp;


import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView适配器
 * Created by hexiao on 2017/11/7.
 */

public class MultipleRecycleAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup ,OnItemClickListener{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     *
     */
    private boolean isInitBanners=false;
    private MultipleRecycleAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecycleAdapter create(List<MultipleItemEntity> data){
        return new MultipleRecycleAdapter(data);
    }

    public static MultipleRecycleAdapter create(DataConverter dataConverter){
        return new MultipleRecycleAdapter(dataConverter.convert());
    }

    //设置是显示一条数据还是显示两条数据
    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFieldsEnum.SPAN_SIZE);
    }


    //进行数据转换，根据类型来设置
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {

        //取出数据
        String name=null;
        String des=null;
        Integer productId=item.getField(MultipleFieldsEnum.PRODUCT_ID);
        Integer pages=item.getField(MultipleFieldsEnum.PAGE_NUMBER);
        Integer totalNumber=item.getField(MultipleFieldsEnum.TOATLE_NUMBER);
        Long  price=null;
        Integer stock=null;
        String mainImage=null;
       ArrayList<String> banners=null;
        switch (holder.getItemViewType()){
            case ItemType.TEXT://设置文字
                /*des=item.getField(MultipleFieldsEnum.DES);
                Log.i("xxxxxx",des);*/
                holder.setText(R.id.text_single,(String)item.getField(MultipleFieldsEnum.DES));
                break;
            case ItemType.IMAGE://设置图片
                mainImage=item.getField(MultipleFieldsEnum.MIAN_IMAGE);
                GlideApp.with(mContext)
                        .load(URL.IMAGE_PRIX+mainImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .into((ImageView) holder.getView(R.id.image_single));
                break;
            case ItemType.IMAGE_TEXT://设置图片加文字
                name=item.getField(MultipleFieldsEnum.NAME);
                des=item.getField(MultipleFieldsEnum.DES);
                price=item.getField(MultipleFieldsEnum.PRICE);
                stock=item.getField(MultipleFieldsEnum.STOCK);
                holder.setText(R.id.text_mulitple,name+" "+des+"\n"+"价格：" +price+" 数量："+stock+"件");
                mainImage=item.getField(MultipleFieldsEnum.MIAN_IMAGE);
                GlideApp.with(mContext)
                        .load(URL.IMAGE_PRIX+mainImage)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .into((ImageView) holder.getView(R.id.image_mulitple));
                break;
            case ItemType.BANNER://轮播图
                if (!isInitBanners){
                    //轮播图的使用
                    banners=item.getField(MultipleFieldsEnum.BANNERS);
                    final ConvenientBanner convenientBanner=holder.getView(R.id.banners_single);
                    BannerCreator.setDefault(convenientBanner,banners,this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    private void init(){
        //设置不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE,R.layout.item_multiple_image);
        addItemType(ItemType.IMAGE_TEXT,R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER,R.layout.item_multiple_banners);

        //设置宽度监听
        setSpanSizeLookup(this);
        //动画效果
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    //轮播图的监听事件
    @Override
    public void onItemClick(int position) {

    }
}


