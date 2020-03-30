package com.example.jy_game;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridView mGridview;

    private static final String TAG = "MainActivity";
    List<String>  stringList = new ArrayList<>();

    String rootPath ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        final AssetManager assets = getResources().getAssets();
        try {
            final String[] list = assets.list("");

            String path = list[0];

            final String[] twoPath = assets.list(path);

            for (String s : twoPath) {
                stringList.add( path+"/"+s);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        mGridview = (GridView) findViewById(R.id.gridview);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,WholeActivity.class));
            }
        });
        mGridview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return stringList.size();
            }

            @Override
            public Object getItem(int position) {
                return stringList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                final String s = stringList.get(position);
                Log.d(TAG, "getView: "+s);

                final ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                try {
                    imageView.setImageBitmap(BitmapFactory.decodeStream(assets.open(s)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return imageView;
            }
        });
    }
}
