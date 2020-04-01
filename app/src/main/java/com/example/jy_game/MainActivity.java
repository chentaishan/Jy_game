package com.example.jy_game;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridView mGridview;

    private static final String TAG = "MainActivity";

    int[] gridDrawable = {
             R.drawable.identicalmatching
            ,R.drawable.expressivelabeling
            , R.drawable.similarmatching
            , R.drawable.pictureidentification
            , R.drawable.receptivelabeling
            ,R.drawable.sorting};

    String[] names = {"完全匹配"
            , "相似匹配"
            , "分类辨识"
            , "认知考核"
            , "图片重复读音"
            , "图片分类"
            };
    String rootPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {


        mGridview = findViewById(R.id.gridview);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Intent intent = new Intent(MainActivity.this, WholeActivity.class);

                startActivity(intent);
            }
        });
        mGridview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return gridDrawable.length;
            }

            @Override
            public Object getItem(int position) {
                return gridDrawable[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {


                View root = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_grid_item,null);
                final int drawable = gridDrawable[position];

                final ImageView imageView =root.findViewById(R.id.img);
                final TextView name =root.findViewById(R.id.name);
                name.setText(names[position]);


                imageView.setImageResource(drawable);

                return root;
            }
        });
    }
}
