package com.example.jy_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LikeActivity extends BaseActivity implements View.OnTouchListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
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


        setPicView(mImageView, mGridview);
    }

    @Override
    protected void ifLike(View view) {
        String group = hostDrawable.substring(0, hostDrawable.lastIndexOf("/"));
        String tag = view.getTag() + "";
        tag = tag.substring(0, tag.lastIndexOf("/"));

        if (view != null && group.equals(tag)) {


            Toast.makeText(this, "哎呦，不错哦！", Toast.LENGTH_SHORT).show();

            getRandomNum();
            mTop = 0;
            mLeft = 0;
            l = DensityUtil.getScreenWidth(this) - layoutParams.width;
            setImageViewMargin(l / 2, l / 2);
            setPicView(mImageView, mGridview);

        } else {


            Toast.makeText(this, "不对啊！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void ifSame(View view) {

    }
}
