package com.example.jy_game.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jy_game.DensityUtil;
import com.example.jy_game.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片分类转载容器
 */
public class SortTypeLayout extends LinearLayout {
    private List<String> listBeans = new ArrayList<>();

    public SortTypeLayout(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public SortTypeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public SortTypeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    private int columnNum = 2;
    private static int imageWidth = 0;
    private LinearLayout rowLinearlayout;
    int totalDividerWidth = 30 * 2;


    public void setGroupName(String name){

        final TextView textView = new TextView(getContext());
        textView.setBackgroundColor(Color.parseColor("#AEEEEE"));
        textView.setTextColor(Color.parseColor("#ffffff"));
        addView(textView);
        textView.setText(name);
        textView.setPadding(22,0,0,0);

        final LayoutParams layoutParams1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.gravity = Gravity.CENTER_HORIZONTAL;
        textView.setLayoutParams(layoutParams1);

    }
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

    public void addGridList(Activity context, final String itempath, IUpdateUIListener iUpdateUIListener) throws IllegalAccessException {
        if (columnNum == 0) {
            throw new IllegalAccessException("请设置列数！");
        }
        this.iUpdateUIListener = iUpdateUIListener;
        imageWidth = DensityUtil.getScreenWidth(context) - totalDividerWidth;
        imageWidth = imageWidth / columnNum;
        removeAllViews();

        listBeans.add(itempath);
        if (listBeans.size()==0){
            return;
        }

        String t = listBeans.get(listBeans.size() - 1);

        switch ((listBeans.size()-1) % columnNum) {

            case 0:


                View item = initItemView(t);

                rowLinearlayout = new LinearLayout(getContext());
                rowLinearlayout.setOrientation(HORIZONTAL);
                LayoutParams layout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setMargins(0, 0, 0, 30);
                rowLinearlayout.setLayoutParams(layout);

                rowLinearlayout.addView(item);

                addView(rowLinearlayout);
                break;
            default:


                item = initItemView(t);


                //添加间隔线
                View view = new View(getContext());
                view.setLayoutParams(new LayoutParams(30, ViewGroup.LayoutParams.MATCH_PARENT));
                rowLinearlayout.addView(view);
                rowLinearlayout.addView(item);

                break;

        }

    }

    private <T> View initItemView(T t) {
        //获取子布局，设置宽度 高度

        View item = LayoutInflater.from(getContext()).inflate(R.layout.category_goods_item, null);
        LayoutParams linearParams = new LayoutParams(imageWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        item.setLayoutParams(linearParams);

        // 设置图片宽高 正方形
        ImageView imageView = item.findViewById(R.id.image);

        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = imageWidth;
        layoutParams.height = imageWidth;

//        imageView.setBackgroundColor(Color.parseColor("#F0EBE9"));
        imageView.setLayoutParams(layoutParams);

        TextView title = item.findViewById(R.id.title);


        iUpdateUIListener.setItem(t, imageView);


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
