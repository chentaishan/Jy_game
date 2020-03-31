package com.example.jy_game;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import androidx.appcompat.app.AppCompatActivity;

public class WholeActivity extends AppCompatActivity implements View.OnTouchListener {

    private TextView mName;
    private ImageView mImageView;
    private GridView mGridview;

    int l, t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (this.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, this.getResources().getDisplayMetrics());
            Log.i(TAG, "------------actionBarHeight=" + actionBarHeight);
        }
        final int size = DensityUtil.dp2px(this, 222f);

        l = DensityUtil.getScreenWidth(this) -size;
        t = DensityUtil.getScreenHeight(this) -size - actionBarHeight-DensityUtil.getStatusBarHeight(this);

        Log.d(TAG, "onResume: "+t);

        setImageViewMargin(l/2,l/2);

    }

    private void initView() {
        mName = findViewById(R.id.name);
        mImageView = findViewById(R.id.image);
        mGridview = findViewById(R.id.gridview);
        mImageView.setOnTouchListener(this);

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
