package com.example.jy_game;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jy_game.view.SortTypeLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    int defaultTopDP = 44;
    int defaultHostWidthDP = 111;

    int defaultLeftPX;
    int defaultTopPX = 44;

    int maxLeftMargin;
    int maxTopMargin;
    private TextToSpeech tts;
    private final int END_NEXT = 1;
    private final int RETRY_NEXT = 2;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        defaultLeftPX = (DensityUtil.getScreenWidth(this) - DensityUtil.dp2px(this, defaultHostWidthDP)) / 2;
        defaultTopPX = DensityUtil.dp2px(this, defaultTopDP);

        maxLeftMargin = DensityUtil.getScreenWidth(this) - DensityUtil.dp2px(this, defaultHostWidthDP);
        maxTopMargin = DensityUtil.getScreenHeight(this) - DensityUtil.dp2px(this, defaultHostWidthDP) - getNavigationBarHeight();

        initSound();
        initView();

        getGroupName();
        getGroup_subFile();
        getHostpic();

        setGroupname();
    }

    private int getNavigationBarHeight() {

        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        int height = getResources().getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }

    public void initPop(String msg) {

        View root = LayoutInflater.from(this).inflate(R.layout.pop_item, null);
        popupWindow = new PopupWindow(root, ViewGroup.LayoutParams.MATCH_PARENT, 200);

        TextView title = root.findViewById(R.id.tips);
        title.setText(msg);
        popupWindow.showAtLocation(mImage, Gravity.CENTER, 0, 0);

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

                addItem2Layout(mImage, left, top);


                break;
        }
        return true;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
            // 重置数据
            switch (msg.what) {
                case END_NEXT:
                    mLayoutLeft.reset();
                    mLayoutRight.reset();

                    getGroupName();
                    getGroup_subFile();
                    getHostpic();

                    setGroupname();
                    break;
                case RETRY_NEXT:
                    break;
            }

        }
    };

    private void addItem2Layout(ImageView mImage, int left, int top) {

        final View leftView = DensityUtil.addItem2layout(mLayoutLeft, left, top);
        final View rightView = DensityUtil.addItem2layout(mLayoutRight, left, top);
        Log.d(TAG, "addItem2Layout: leftView=" + leftView + "  rightView=" + rightView);
        final String imgPath = (String) mImage.getTag();

        if (leftView != null && imgPath.contains(leftGroupName)) {
            try {
                mLayoutLeft.addGridList(SortActivity.this, currImgPath, new SortTypeLayout.IUpdateUIListener() {
                    @Override
                    public void setItem(Object o, ImageView img) {

                        String path = (String) o;
                        img.setTag(path);

                        img.setImageBitmap(BitmapFactory.decodeFile(path));

                        int count = mLayoutLeft.getCount();

                        if (count == 6) {
                            //   提示 语音  TODO 重置

                            String msg = "好棒";
                            initPop(msg);
                            talkSound(msg);
                            Message message = new Message();
                            message.what = END_NEXT;
                            handler.sendMessageDelayed(message, 1000);

                        }
                    }
                });
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            getHostpic();

        } else if (rightView != null && imgPath.contains(rightGroupName)) {
            try {
                mLayoutRight.addGridList(SortActivity.this, currImgPath, new SortTypeLayout.IUpdateUIListener() {
                    @Override
                    public void setItem(Object o, ImageView img) {


                        String path = (String) o;
                        img.setTag(path);
                        img.setImageBitmap(BitmapFactory.decodeFile(path));

                        int count = mLayoutRight.getCount();
                        if (count == 6) {
                            //   提示 语音  TODO 重置

                            String msg = "好棒";
                            initPop(msg);
                            talkSound(msg);
                            Message message = new Message();
                            message.what = END_NEXT;
                            handler.sendMessageDelayed(message, 1000);

                        }
                    }
                });
                getHostpic();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {

//            mTop=0;
//            mLeft=0;
            anim();
            String msg = "不对哦";
            initPop(msg);
            talkSound(msg);
            Message message = new Message();
            message.what = RETRY_NEXT;
            handler.sendMessageDelayed(message, 500);
//            setImageViewMargin(l,t);
        }
    }

    public void initSound() {
        //初始化TTS
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // 判断是否转化成功
                if (status == TextToSpeech.SUCCESS) {
                    //默认设定语言为中文，原生的android貌似不支持中文。
                    int result = tts.setLanguage(Locale.SIMPLIFIED_CHINESE);

                    Log.d(TAG, "onInit: " + result);
                }
            }
        });


    }

    public void talkSound(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);


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

        Log.d(TAG, "setGroupname: leftgroupname=" + leftGroupName + "--rightgroupName=" + rightGroupName);
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

        mTop = 0;
        mLeft = 0;

        final Random random = new Random();
        final int index = random.nextInt(allPaths.size() - 1);

        currImgPath = allPaths.get(index);
        Log.d(TAG, "getHostpic: " + index + "---path=" + currImgPath);


        mImage.setTag(currImgPath);
        mImage.setImageBitmap(BitmapFactory.decodeFile(currImgPath));

        setImageViewMargin(defaultLeftPX, defaultTopPX);


    }


    protected void getGroupName() {


        final File[] list = MyApp.getFiledir().listFiles();
        final Random random = new Random();
        final int leftIndex = random.nextInt(list.length);
        int rightIndex = random.nextInt(list.length);
        if (leftIndex == rightIndex) {
            rightIndex = random.nextInt(list.length);
        }
        leftGroupName = list[leftIndex].getName();
        rightGroupName = list[rightIndex].getName();

        leftGroupName = checkGroupName(leftGroupName);
        rightGroupName = checkGroupName(rightGroupName);


    }

    /**
     * 如果组名里不包含"-"，证明是有异常
     *
     * @param groupname
     * @return
     * @throws IOException
     */
    public String checkGroupName(String groupname) {
        Log.d(TAG, "checkGroupName: " + groupname);
        if (!groupname.contains("-")) {
            final File[] list = MyApp.getFiledir().listFiles();
            final Random random = new Random();
            final int index = random.nextInt(list.length);
            groupname = list[index].getName();
            return checkGroupName(groupname);
        }
        return groupname;
    }

    public void anim() {

        Log.d(TAG, "anim: mTop=" + mTop + "----defaTop=" + defaultTopPX);

        ValueAnimator valueAnimator1 = ValueAnimator.ofInt(mTop, defaultTopPX);

        valueAnimator1.setDuration(500);

        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Log.d(TAG, "anim: value=" + value);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImage.getLayoutParams();
                layoutParams.topMargin = value;
                if (value - defaultTopPX>0){

                    int progress = (value - defaultTopPX) * 100 / (mTop - defaultTopPX);
                    Log.d(TAG, "anim: progress=" + progress);
                    layoutParams.leftMargin = (mLeft - defaultLeftPX) * progress / 100 + defaultLeftPX;
                    Log.d(TAG, "anim: leftMargin=" + layoutParams.leftMargin);
                }


                mImage.setLayoutParams(layoutParams);
            }
        });


        valueAnimator1.start();
    }


    public void getGroup_subFile() {

        allPaths.clear();
        final File[] leftList = new File(MyApp.getFiledir() + File.separator + leftGroupName).listFiles();
        for (File path : leftList) {
            if (path.getName().contains(".jpg"))
                allPaths.add(path.getAbsolutePath());
        }
        final File[] rightList = new File(MyApp.getFiledir() + File.separator + rightGroupName).listFiles();

        for (File path : rightList) {
            if (path.getName().contains(".jpg"))
                allPaths.add(path.getAbsolutePath());
        }


    }
}
