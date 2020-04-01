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

public class WholeActivity extends BaseActivity implements View.OnTouchListener {




    protected TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole);


        initView();
    }

    @Override
    protected void initView() {
        getRandomNum();

        mName = findViewById(R.id.name);
        mImageView = findViewById(R.id.image);
        mImageView.setOnTouchListener(this);
        mGridview = findViewById(R.id.gridview);
        mGridview.setColumnNum(3);


        setPicView(mImageView,mGridview);
    }

    @Override
    protected void ifLike(View view) {

    }

    @Override
    protected void ifSame(View view) {
        if (view != null&&hostDrawable.equals(view.getTag())) {


            Toast.makeText(this, "哎呦，不错哦！", Toast.LENGTH_SHORT).show();

            getRandomNum();
            mTop =0;
            mLeft =0;
            l = DensityUtil.getScreenWidth(this) - layoutParams.width;
            setImageViewMargin(l / 2, l / 2);
            setPicView(mImageView,mGridview);

        } else {


            Toast.makeText(this, "不对啊！", Toast.LENGTH_SHORT).show();
        }
    }


}
