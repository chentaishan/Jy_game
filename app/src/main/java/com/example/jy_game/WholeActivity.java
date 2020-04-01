package com.example.jy_game;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.RelativeLayout.CENTER_HORIZONTAL;

public class WholeActivity extends AppCompatActivity implements View.OnTouchListener {


    private ImageView mImageView;
    private MySelfGridView mGridview;
    List<String> drawablePaths = new ArrayList<>();
    int l, t;

    String hostDrawable;
    //    当前对比的是第几张
    int currPic;
    int maxPics = 6;
    private TextView mName;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole);

        getRandomNum();

        mName = findViewById(R.id.name);
        mImageView = findViewById(R.id.image);
        mImageView.setOnTouchListener(this);
        mGridview = findViewById(R.id.gridview);
        mGridview.setColumnNum(3);


        setPicView();


    }

    /**
     * 获取随机数 和设置图片
     */
    public void getRandomNum() {

        final int randomIndex = MyApp.stringList.size() - 1;

        drawablePaths.clear();
        for (int i = 0; i < maxPics - 1; i++) {
            final Random random = new Random();
            final int index = random.nextInt(randomIndex);

            drawablePaths.add(MyApp.stringList.get(index));
        }
        hostDrawable = MyApp.stringList.get(currPic);

        final Random random = new Random();
        final int index = random.nextInt(5);

        drawablePaths.add(index, hostDrawable);

        currPic++;
    }

    @Override
    protected void onResume() {
        super.onResume();

        layoutParams = (RelativeLayout.LayoutParams) mImageView.getLayoutParams();

        layoutParams.width = mGridview.getImageItemWidth();
        layoutParams.height = mGridview.getImageItemWidth();
        mImageView.setLayoutParams(layoutParams);

        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (this.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, this.getResources().getDisplayMetrics());
            Log.i(TAG, "------------actionBarHeight=" + actionBarHeight);
        }

        l = DensityUtil.getScreenWidth(this) - layoutParams.width;
        t = DensityUtil.getScreenHeight(this) - layoutParams.width
                - actionBarHeight ;

        Log.d(TAG, "onResume: " + t);

        setImageViewMargin(l / 2, l / 2);

    }

    private void setPicView() {

        try {
            mImageView.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(hostDrawable)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mGridview.initGridList(this, drawablePaths, new MySelfGridView.IUpdateUIListener() {
                @Override
                public void setItem(Object o, ImageView img) {

                    String path = (String) o;
                    try {
                        img.setTag(path);
                        img.setImageBitmap(BitmapFactory.decodeStream(getResources().getAssets().open(path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //初始化开始位置
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                mTop = mImageView.getTop();
                mLeft = mImageView.getLeft();
                break;
            case MotionEvent.ACTION_MOVE:
                //手势移动的dX和dY为控件的marginLeft和marginTop
                int moveX = (int) event.getRawX();
                int moveY = (int) event.getRawY();
                int dX = moveX - startX;
                int dY = moveY - startY;
                setImageViewMargin(dX, dY);
                break;
            case MotionEvent.ACTION_UP:
                //初始值重置
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                mTop = mImageView.getTop();
                mLeft = mImageView.getLeft();


                int[] location = new int[2];
                mImageView.getLocationOnScreen(location);
                int left = location[0];
                int top = location[1];

                Log.d(TAG, "onTouch: onTop=" + mTop + "  mLeft=" + mLeft);
                final View viewAtActivity = DensityUtil.findViewByXY(mGridview, left, top);


                if (viewAtActivity != null&&hostDrawable.equals(viewAtActivity.getTag())) {

                    Toast.makeText(this, "哎呦，不错哦！", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onTouch: UP" + viewAtActivity == null ? "null" : viewAtActivity.getTag() + "");

                    getRandomNum();
                    mTop =0;
                    mLeft =0;
                    l = DensityUtil.getScreenWidth(this) - layoutParams.width;
                    setImageViewMargin(l / 2, l / 2);
                    setPicView();

                } else {


                    Toast.makeText(this, "不对啊！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return true;
    }

    private static final String TAG = MainActivity.class.getSimpleName();
    private int startX = 0;
    private int startY = 0;
    private int mTop;
    private int mLeft;
    private int mMeasuredWidth;
    private int mMeasuredHeight;

    /**
     * 动态设置控件的marginTop 和 marginLeft的值
     *
     * @param dX x轴的偏移量
     * @param dY y轴的偏移量
     */
    private void setImageViewMargin(int dX, int dY) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
        int top = mTop + dY;
        int left = mLeft + dX;

        Log.d(TAG, "setImageViewMargin: top=" + top + "   left=" + left);
        //设置left和top的边界值
        if (left < 0) {
            left = 0;
        } else if (left > l) {
            left = l;
        }
        if (top < 0) {
            top = 0;
        } else if (top > t) {
            top = t;
        }
        layoutParams.topMargin = top;
        layoutParams.leftMargin = left;
        mImageView.setLayoutParams(layoutParams);
    }


}
