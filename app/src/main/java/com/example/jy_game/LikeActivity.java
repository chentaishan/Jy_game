package com.example.jy_game;


import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class LikeActivity extends BaseActivity implements View.OnTouchListener {

    private static final String TAG = "LikeActivity";
    //    当前对比的是第几张
    int currPic;
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
    protected void getEachHostPic() {
        hostDrawable = MyApp.stringList.get(currPic);

//        final Random random = new Random();
//        final int index = random.nextInt(10000)%(maxPics);
//        Log.d(TAG, "当前主图片随机插位: "+index);
//        drawablePaths[index]=hostDrawable;

        currPic++;


    }

    @Override
    public void getEachPageList() {

        // 获取当前相似图片
        final String substring = hostDrawable.substring(0,hostDrawable.lastIndexOf("/"));
        Log.d(TAG, "getEachPageList: 截取路径="+substring);
        try {
            final String[] files = getAssets().list(substring);
            final Random random = new Random();
            final int index = random.nextInt(files.length);
            Log.d(TAG, "当前相似图片随机插位: "+index);
            drawablePaths[index%maxPics]=substring+"/"+files[index];
//            .set(index%maxPics,files[index]);

        } catch (IOException e) {
            e.printStackTrace();
        }




        for (int x=0;x<drawablePaths.length;x++  ) {
            final String path = drawablePaths[x];

            if (TextUtils.isEmpty(path)){

                noDoubleAddItem(x );
            }

        }

    }



    public void noDoubleAddItem(int index){
        final int size = MyApp.stringList.size();


        int random=  new Random().nextInt(size);
        String p = MyApp.stringList.get(random);
        Log.d(TAG, "随机获取图片插入--name=: "+p);
        if (!isContains(p)&&!p.equals(hostDrawable)){
            drawablePaths[index] = p;
        }else{

            noDoubleAddItem(index);
        }
    }

    public boolean isContains(String path){
        for (String drawablePath : drawablePaths) {
            if (!TextUtils.isEmpty(drawablePath)&&drawablePath.equals(path)){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void ifLike(View view) {

        if (view==null){
            anim();
            return;
        }
        Log.d(TAG, "ifLike: "+view);
        String group = hostDrawable.substring(0, hostDrawable.lastIndexOf("/"));
        String tag = view.getTag() + "";
        tag = tag.substring(0, tag.lastIndexOf("/"));

        if (view != null && group.equals(tag)) {


            Toast.makeText(this, "哎呦，不错哦！", Toast.LENGTH_SHORT).show();

            getRandomNum();
            mTop = 0;
            mLeft = 0;
            l = DensityUtil.getScreenWidth(this) - layoutParams.width;
            setImageViewMargin(l / 2, defaultHeight);
            setPicView(mImageView, mGridview);

        } else {

            anim();

            Toast.makeText(this, "不对啊！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void ifSame(View view) {

    }


}
