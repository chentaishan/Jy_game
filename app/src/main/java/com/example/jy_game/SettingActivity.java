package com.example.jy_game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jy_game.utils.SpUtils;

import static com.example.jy_game.utils.Constants.PIC_NUM;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mPlus;
    private TextView mNum;
    private TextView mMinus;
    private int pic_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        initView();
        initData();
    }

    private void initData() {
        pic_num = SpUtils.getInstance().getInt(PIC_NUM);

        mNum.setText(pic_num + "");
    }

    private void initView() {
        mPlus = (TextView) findViewById(R.id.plus);
        mNum = (TextView) findViewById(R.id.num);
        mMinus = (TextView) findViewById(R.id.minus);
        mPlus.setOnClickListener(this);
        mMinus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus:
                // TODO 20/04/09
                if (pic_num < 6) {

                    pic_num++;
                    mNum.setText(pic_num + "");
                    SpUtils.getInstance().setValue(PIC_NUM, pic_num);
                }
                break;
            case R.id.minus:
                // TODO 20/04/09
                if (pic_num > 1) {

                    pic_num--;
                    mNum.setText(pic_num + "");
                    SpUtils.getInstance().setValue(PIC_NUM, pic_num);
                }
                break;
            default:
                break;
        }
    }
}
