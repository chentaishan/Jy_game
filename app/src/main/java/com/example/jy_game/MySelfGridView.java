package com.example.jy_game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

public class MySelfGridView extends LinearLayout {
    public MySelfGridView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public MySelfGridView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public MySelfGridView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    private int columnNum = 2;
    private static int imageWidth = 0;
    private LinearLayout rowLinearlayout;
    int totalDividerWidth = 30 * 2;

    /**
     * 设置所有间隔距离，方便计算item的宽度
     *
     * @param totalDividerWidth
     */
    public void setTotalDividerWidth(int totalDividerWidth) {
        this.totalDividerWidth = totalDividerWidth;
    }

    public static int getImageItemWidth() {
        return imageWidth;
    }

    /**
     * 设置列数
     *
     * @param columnNum
     */
    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public <T> void initGridList(Activity context, final T[] listBeans, IUpdateUIListener iUpdateUIListener) throws IllegalAccessException {
        if (columnNum == 0) {
            throw new IllegalAccessException("请设置列数！");
        }
        this.iUpdateUIListener = iUpdateUIListener;
        imageWidth = DensityUtil.getScreenWidth(context) - totalDividerWidth;
        imageWidth = imageWidth / columnNum;
        removeAllViews();
        for (int i = 0; i < listBeans.length; i++) {

            T t = listBeans[i];

            View item = initItemView(t);

            if (i % columnNum == 0) {
                rowLinearlayout = new LinearLayout(getContext());
                rowLinearlayout.setOrientation(HORIZONTAL);
                LayoutParams layout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setMargins(0, 0, 0, 30);
                rowLinearlayout.setLayoutParams(layout);

                rowLinearlayout.addView(item);

                addView(rowLinearlayout);

            } else {
                //添加间隔线
                View view = new View(getContext());
                view.setLayoutParams(new LayoutParams(30, ViewGroup.LayoutParams.MATCH_PARENT));
                rowLinearlayout.addView(view);
                rowLinearlayout.addView(item);
            }


        }

    }
    private <T>View initItemView(T t){
        //获取子布局，设置宽度 高度

        View item = LayoutInflater.from(getContext()).inflate(R.layout.category_goods_item, null);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(imageWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        item.setLayoutParams(linearParams);

        // 设置图片宽高 正方形
        ImageView imageView = item.findViewById(R.id.image);

        LinearLayout.LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = imageWidth;
        layoutParams.height = imageWidth;

        imageView.setLayoutParams(layoutParams);


        iUpdateUIListener.setItem(t, imageView );


        return item;
    }
    float gaussianBlurValue;
    public void refreshImageView(String path,float gaussianBlurValue){
        this.gaussianBlurValue = gaussianBlurValue;
        if (this.gaussianBlurValue==0){
            return;
        }
        if (!TextUtils.isEmpty(path)){
            for (int i = 0; i < getChildCount(); i++) {
                View item = getChildAt(i);
                if (item instanceof ViewGroup){
                    findImageViewByGroup((ViewGroup) item,path);
                }

            }
        }


    }

    private void findImageViewByGroup(ViewGroup viewGroup,String path) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View item = viewGroup.getChildAt(i);
            if (item instanceof ImageView){

                ImageView img = (ImageView) item;
                final  String p = (String) img.getTag();
                if (gaussianBlurValue!=0&&!p.equals(path)){

                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getResources().getAssets().open(p));
                        bitmap = Imageutils.blurBitmap(getContext(),bitmap,gaussianBlurValue);
                        img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }else if(item instanceof ViewGroup){
                findImageViewByGroup(viewGroup,path);
            }
        }

    }

    IUpdateUIListener iUpdateUIListener;

    public interface IUpdateUIListener<T> {

        void setItem(T t, ImageView img);
    }


}
