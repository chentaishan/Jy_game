package com.example.jy_game;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jy_game.utils.SpUtils;

import static com.example.jy_game.utils.Constants.PIC_NUM;
import static com.example.jy_game.utils.Constants.UNSELECTED_IMGS_SP_KEY;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mPlus;
    private TextView mNum;
    private TextView mMinus;
    private int pic_num;
    private ListView mListview;
    private ViewHolder viewHolder;
    private String unselect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        initView();
        initData();

    }

    private static final String TAG = "SettingActivity";
    private void initData() {
        pic_num = SpUtils.getInstance().getInt(PIC_NUM);
        mNum.setText(pic_num + "");

        // 未选中的所有图片
        unselect = SpUtils.getInstance().getString(UNSELECTED_IMGS_SP_KEY);


        mListview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return MyApp.stringList.size();
            }

            @Override
            public Object getItem(int position) {
                return MyApp.stringList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.list_item, null);

                    viewHolder.img = convertView.findViewById(R.id.img);
                    viewHolder.checkBox = convertView.findViewById(R.id.checkbox);


                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                final String name = MyApp.stringList.get(position);
                final String filename = name.substring(name.lastIndexOf("/")+1);
                Log.d(TAG, "getView: "+filename);
                if (unselect.contains(filename)){
                    viewHolder.checkBox.setChecked(false);
                }else{
                    viewHolder.checkBox.setChecked(true);
                }
                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (!unselect.contains(filename)){
                            viewHolder.checkBox.setChecked(false);

                            if (!unselect.contains(filename)){
                                unselect =unselect+ filename+",";
                            }

                        }else{
                            viewHolder.checkBox.setChecked(true);

                            if (unselect.contains(filename+",")){

                                unselect =unselect.replace(filename+",","");
                            }
                        }
                        SpUtils.getInstance().setValue(UNSELECTED_IMGS_SP_KEY,unselect);
                        notifyDataSetChanged();
                    }
                });
                viewHolder.img.setImageBitmap(BitmapFactory.decodeFile(name));

                return convertView;
            }
        });
    }

    private void initView() {
        mListview = (ListView) findViewById(R.id.listview);
        View header = LayoutInflater.from(this).inflate(R.layout.setting_header,null);
        mListview.addHeaderView(header);
        mPlus = (TextView) header.findViewById(R.id.plus);
        mNum = (TextView) header.findViewById(R.id.num);
        mMinus = (TextView) header.findViewById(R.id.minus);
        mPlus.setOnClickListener(this);
        mMinus.setOnClickListener(this);

    }

    class ViewHolder {

        ImageView img;
        CheckBox checkBox;
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
