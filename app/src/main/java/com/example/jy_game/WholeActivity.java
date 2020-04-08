package com.example.jy_game;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class WholeActivity extends BaseActivity implements View.OnTouchListener {


    private static final String TAG = "WholeActivity";

    protected TextView mName;
    //    当前对比的是第几张
    int currPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole);


        initSound();
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
    protected void getEachHostPic() {
        hostDrawable = MyApp.stringList.get(currPic);

        final Random random = new Random();
        final int index = random.nextInt(10000)%(maxPics);
        Log.d(TAG, "当前主图片随机插位: "+index);
        drawablePaths[index]=hostDrawable;

        currPic++;


    }

    @Override
    public void getEachPageList() {
        final int randomIndex = MyApp.stringList.size() - 1;


        for (int x=0;x<drawablePaths.length;x++  ) {
            final String path = drawablePaths[x];

            if (TextUtils.isEmpty(path)){
                final Random random = new Random();
                final int index = random.nextInt(randomIndex);
                Log.d(TAG, "getRandomNum: "+index);
                String p = MyApp.stringList.get(index);

                drawablePaths[x]=p;
            }

        }

    }

    @Override
    protected void ifLike(View view) {

    }

    @Override
    protected void ifSame(View view) {
        if (view==null){
            anim();
            gaussianBlurValue+=2;
            mGridview.refreshImageView(hostDrawable,gaussianBlurValue);
            return;
        }
        if (view != null&&hostDrawable.equals(view.getTag())) {


            talkSound("哎呦，不错");
            Toast.makeText(this, "哎呦，不错哦！", Toast.LENGTH_SHORT).show();

            getRandomNum();
            mTop =0;
            mLeft =0;
            l = DensityUtil.getScreenWidth(this) - layoutParams.width;
            setImageViewMargin(defaultLeft, DensityUtil.dp2px(this,defaultHeight));
            setPicView(mImageView,mGridview);

        } else {

            anim();
            talkSound("不对啊");
            gaussianBlurValue+=2;
            mGridview.refreshImageView(hostDrawable,gaussianBlurValue);
            Toast.makeText(this, "不对啊！", Toast.LENGTH_SHORT).show();
        }
    }


}
