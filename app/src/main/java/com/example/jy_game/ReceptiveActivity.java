package com.example.jy_game;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jy_game.net.CallBack;
import com.example.jy_game.net.NetUtils;
import com.example.jy_game.view.MySelfGridView;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;

/**
 * 听声辨物
 */
public class ReceptiveActivity extends AppCompatActivity {

    private MySelfGridView mGridview;
    private TextView mName;
    private int currPic = 0;
    private String hostDrawable;
    private String[] drawablePaths;
    private int maxPics = 6;
    private static final String TAG = "ReceptiveActivity";
    private TextToSpeech tts;
    //当前物体的关键字
    private String name;
    Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initData();

            initSound();
            String[] ps = hostDrawable.split("_");
            name = ps[ps.length - 2];

            NetUtils.getNetData(name, new CallBack() {
                @Override
                public void updateSuccess(String result) {
                    mName.setText(result);
                    talkSound(result);
                }

                @Override
                public void updateFailed(String error) {

                }
            });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptive);

        initView();

        initData();

        initSound();
        String[] ps = hostDrawable.split("_");
        name = ps[ps.length - 2];

        NetUtils.getNetData(name, new CallBack() {
            @Override
            public void updateSuccess(String result) {
                mName.setText(result);
                talkSound(result);
            }

            @Override
            public void updateFailed(String error) {

            }
        });
    }


    private void initData() {

        drawablePaths = new String[6];

        getCurrPic();
        getEachPageList();

        try {
            mGridview.initGridList(this, drawablePaths, new MySelfGridView.IUpdateUIListener() {
                @Override
                public void setItem(Object o, ImageView img) {
                    final String path = (String) o;

                    Log.d(TAG, "setItem: 图片地址=" + path);
                    try {
                        img.setTag(path);
                        img.setImageBitmap(BitmapFactory.decodeStream(getResources().getAssets().open(path)));
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (path.contains(name)) {
                                    talkSound("太棒了");


                                    handler.sendMessageDelayed(Message.obtain(), 1000);

                                } else {

                                    talkSound("不对哦");
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void getEachPageList() {
        final int randomIndex = MyApp.stringList.size() - 1;


        for (int x = 0; x < drawablePaths.length; x++) {
            final String path = drawablePaths[x];

            if (TextUtils.isEmpty(path)) {
                final Random random = new Random();
                final int index = random.nextInt(randomIndex);
                Log.d(TAG, "getRandomNum: " + index);
                String p = MyApp.stringList.get(index);

                drawablePaths[x] = p;
            }

        }

    }

    public void getCurrPic() {
        final int size = MyApp.stringList.size();


        final Random random = new Random();
        final int index = random.nextInt(size);
        hostDrawable = MyApp.stringList.get(index);

        Log.d(TAG, "当前主图片随机插位: " + hostDrawable);
        drawablePaths[index % maxPics] = hostDrawable;

        currPic++;


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


    private void initView() {
        mGridview = (MySelfGridView) findViewById(R.id.gridview);
        mName = (TextView) findViewById(R.id.name);
    }

}
