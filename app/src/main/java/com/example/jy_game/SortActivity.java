package com.example.jy_game;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.jy_game.view.SortTypeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 图片分类
 */
public class SortActivity extends AppCompatActivity implements View.OnTouchListener {

    private String hostDrawable;

    String leftGroupName, rightGroupName;
    List<String> allPaths = new ArrayList<>();
    private ImageView mImage;
    private SortTypeLayout mLayoutLeft;
    private SortTypeLayout mLayoutRight;
    private String currImgPath;
    private int startX;
    private int startY;
    private int mTop;
    private int mLeft;
    // 默认左边距，顶部边距
    private int l,t;
    int defaultLeft = 111;
    int defaultTop = 44;

    int maxLeftMargin;
    int maxTopMargin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        l = (DensityUtil.getScreenWidth(this)-DensityUtil.dp2px(this,defaultLeft))/2;
        t = DensityUtil.dp2px(this,defaultTop);

        maxLeftMargin = DensityUtil.getScreenWidth(this)-DensityUtil.dp2px(this,defaultLeft);
        maxTopMargin = DensityUtil.getScreenHeight(this)-DensityUtil.dp2px(this,defaultLeft)-getNavigationBarHeight();


        Log.d(TAG, "onCreate: l="+l+"  t="+t);
        initView();

        getGroupName();
        getGroup_subFile();
        getHostpic();

        setGroupname();
    }
    private int getNavigationBarHeight() {

        int resourceId = getResources().getIdentifier("navigation_bar_height","dimen", "android");
        int height = getResources().getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }

    public int getStatusBarHeight(){
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (this.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, this.getResources().getDisplayMetrics());
            Log.i(TAG, "------------actionBarHeight=" + actionBarHeight);
        }

        return actionBarHeight;
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //初始化开始位置
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                mTop = mImage.getTop();
                mLeft = mImage.getLeft();
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
                mTop = mImage.getTop();
                mLeft = mImage.getLeft();


                int[] location = new int[2];
                mImage.getLocationOnScreen(location);
                int left = location[0];
                int top = location[1];

                addItem2Layout(mImage,left,top);


                break;
        }
        return true;
    }

    private void addItem2Layout(ImageView mImage,int left, int top) {

        final View leftView = DensityUtil.addItem2layout(mLayoutLeft, left, top);
        final View rightView = DensityUtil.addItem2layout(mLayoutRight, left, top);
        Log.d(TAG, "addItem2Layout: leftView=" + leftView + "  rightView=" + rightView);
        final String imgPath   = (String) mImage.getTag();

        if (leftView!=null&&imgPath.contains(leftGroupName)){
            try {
                mLayoutLeft.addGridList(SortActivity.this, currImgPath, new SortTypeLayout.IUpdateUIListener() {
                    @Override
                    public void setItem(Object o, ImageView img) {

                        String path = (String) o;
                        img.setTag(path);
                        try {
                            img.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(path)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            getHostpic();

        } else if (rightView!=null&&imgPath.contains(rightGroupName)){
            try {
                mLayoutRight.addGridList(SortActivity.this, currImgPath, new SortTypeLayout.IUpdateUIListener() {
                    @Override
                    public void setItem(Object o, ImageView img) {


                        try {
                            String path = (String) o;
                            img.setTag(path);
                            img.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(path)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                getHostpic();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else{

            mTop=0;
            mLeft=0;

            setImageViewMargin(l,t);
        }
    }
    /**
     * 动态设置控件的marginTop 和 marginLeft的值
     *
     * @param dX x轴的偏移量
     * @param dY y轴的偏移量
     */
    protected void setImageViewMargin(int dX, int dY) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImage.getLayoutParams();
        int top = mTop + dY;
        int left = mLeft + dX;

        Log.d(TAG, "setImageViewMargin: top=" + top + "   left=" + left);
        //设置left和top的边界值
        if (left < 0) {
            left = 0;
        } else if (left > maxLeftMargin) {
            left = maxLeftMargin;
        }
        if (top < 0) {
            top = 0;
        } else if (top > maxTopMargin) {
            top = maxTopMargin;
        }
        layoutParams.topMargin = top;
        layoutParams.leftMargin = left;
        mImage.setLayoutParams(layoutParams);
    }
    public void setGroupname() {

        Log.d(TAG, "setGroupname: leftgroupname="+leftGroupName+"--rightgroupName="+rightGroupName);
        mLayoutLeft.setGroupName(leftGroupName.split("-")[1]);
        mLayoutRight.setGroupName(rightGroupName.split("-")[1]);
    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.image);
        mImage.setOnTouchListener(this);
        mLayoutLeft = (SortTypeLayout) findViewById(R.id.left_layout);
        mLayoutRight = (SortTypeLayout) findViewById(R.id.right_layout);
    }


    private static final String TAG = "SortActivity";

    /**
     * 获取主图片
     */
    public void getHostpic() {

        mTop=0;
        mLeft=0;

        final Random random = new Random();
        final int index = random.nextInt(allPaths.size() - 1);

        currImgPath = allPaths.get(index);
        Log.d(TAG, "getHostpic: " + index + "---path=" + currImgPath);

        try {
            mImage.setTag(currImgPath);
            mImage.setImageBitmap(BitmapFactory.decodeStream(getAssets().open(currImgPath)));

            setImageViewMargin(l,t);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    protected void getGroupName() {

        try {
            final String[] list = getAssets().list("");
            final Random random = new Random();
            final int leftIndex = random.nextInt(list.length);
            int rightIndex = random.nextInt(list.length);
            if (leftIndex == rightIndex) {
                rightIndex = random.nextInt(list.length);
            }
            leftGroupName = list[leftIndex];
            rightGroupName = list[rightIndex];

            leftGroupName =checkGroupName(leftGroupName);
            rightGroupName = checkGroupName(rightGroupName);

        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    /**
     * 如果组名里不包含"-"，证明是有异常
     * @param groupname
     * @return
     * @throws IOException
     */
    public String checkGroupName(String groupname) throws IOException {
        Log.d(TAG, "checkGroupName: "+groupname);
        if (!groupname.contains("-")){
            final String[] list = getAssets().list("");
            final Random random = new Random();
            final int index = random.nextInt(list.length);
            groupname = list[index];
            return checkGroupName(groupname);
        }
        return groupname;
    }

    public void getGroup_subFile() {

        try {
            final String[] leftList = getAssets().list(leftGroupName);
            for (String path : leftList) {
                if (path.contains(".jpg"))
                    allPaths.add(leftGroupName+"/"+path);
            }
            final String[] rightList = getAssets().list(rightGroupName);
            for (String path : rightList) {
                if (path.contains(".jpg"))
                    allPaths.add(rightGroupName+"/"+path);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
