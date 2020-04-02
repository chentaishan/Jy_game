package com.example.jy_game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.Nullable;

public class MySelfGridView extends LinearLayout {
    public MySelfGridView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public MySelfGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public MySelfGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

//        imageView.setBackgroundColor(Color.parseColor("#F0EBE9"));
        imageView.setLayoutParams(layoutParams);

        TextView title = item.findViewById(R.id.title);


        iUpdateUIListener.setItem(t, imageView );


        return item;
    }

    IUpdateUIListener iUpdateUIListener;

    public interface IUpdateUIListener<T> {

        void setItem(T t, ImageView img);
    }

    class ViewHolder {
        ImageView imageView;

        public ViewHolder(View item) {
            imageView = item.findViewById(R.id.image);
        }
    }


}
