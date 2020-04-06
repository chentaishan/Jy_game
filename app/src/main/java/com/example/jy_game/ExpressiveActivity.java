package com.example.jy_game;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jy_game.net.CallBack;
import com.example.jy_game.net.NetUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Random;

/**
 * 图片识别
 */
public class ExpressiveActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mName;
    private ViewPager mViewpager;
    private String[] drawablePaths;
    private ImageView mLeft;
    private ImageView mRight;

    String name = "";
    String currPath="";
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expressive);
        initView();
        initData();

        initSound();
    }

    private static final String TAG = "ExpressiveActivity";

    public void getEachPageList() {
        final int size = MyApp.stringList.size();

        drawablePaths = new String[size];
        Random random = new Random();

        for (int x = 0; x < size; x++) {
            final String path = drawablePaths[x];

            if (TextUtils.isEmpty(path)) {

                final int index = random.nextInt(size);
                String p = MyApp.stringList.get(index);
                Log.d(TAG, "getRandomNum: 随机的图片地址=" + p);

                drawablePaths[x] = p;
            }

        }

    }

    private void initData() {
        getEachPageList();
        currPath = drawablePaths[0];

        String[] ps = currPath.split("_");
        name = ps[ps.length-2];
        currPos=0;
        //
        mName.setText("你看到了什么？");

        mViewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return drawablePaths.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }


            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View root = LayoutInflater.from(ExpressiveActivity.this).inflate(R.layout.viewpager_item, null);
                container.addView(root);
                ImageView img = root.findViewById(R.id.image);
                try {
                    final InputStream instream =getResources(). getAssets().open(drawablePaths[position]);
                    img.setImageBitmap(BitmapFactory.decodeStream(instream));
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            NetUtils.getNetData(name, new CallBack() {
                                @Override
                                public void updateSuccess(String result) {
                                    Log.d(TAG, "updateSuccess: "+result);
                                    talkSound(result);
                                }

                                @Override
                                public void updateFailed(String error) {

                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return img;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

                container.removeView((View) object);
            }
        });
    }

    private void initView() {
        mName = (TextView) findViewById(R.id.name);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mLeft = (ImageView) findViewById(R.id.left);
        mRight = (ImageView) findViewById(R.id.right);
        mLeft.setOnClickListener(this);
        mRight.setOnClickListener(this);
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currPath = drawablePaths[i];
                String[] ps = currPath.split("_");
                name = ps[ps.length-2];


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    int currPos=0;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left:
                // TODO 20/04/06
                if (currPos>0){
                    currPos--;

                    mViewpager.setCurrentItem(currPos);
                }
                break;
            case R.id.right:
                // TODO 20/04/06
                if (currPos<drawablePaths.length){
                    currPos++;

                    mViewpager.setCurrentItem(currPos);
                }
                break;
            default:
                break;
        }
    }


    public void initSound() {
        //初始化TTS
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // 判断是否转化成功
                if (status == TextToSpeech.SUCCESS){
                    //默认设定语言为中文，原生的android貌似不支持中文。
                    int result = tts.setLanguage(Locale.SIMPLIFIED_CHINESE);

                    Log.d(TAG, "onInit: "+result);
                }
            }
        });


    }

    public void talkSound(String text){
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);


    }
}
