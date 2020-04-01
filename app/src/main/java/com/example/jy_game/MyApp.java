package com.example.jy_game;

import android.app.Application;
import android.content.res.AssetManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

   static List<String> stringList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        final AssetManager assets = getResources().getAssets();
        try {
            final String[] list = assets.list("");

            for (int i = 0; i < list.length; i++) {

                String path = list[i];

                final String[] twoPath = assets.list(path);

                for (String s : twoPath) {
                    if (s.endsWith(".jpg")){
                        stringList.add(path + "/" + s);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
