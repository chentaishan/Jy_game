package com.example.jy_game;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import com.example.jy_game.net.NetUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {

    public static Context context;
    public static List<String> stringList = new ArrayList<>();
    private static File cacheDir;

    @Override
    public void onCreate() {
        super.onCreate();

        this.context = this;

        cacheDir = getCacheDir();


        NetUtils.getImagelist();


    }



    public static File getFiledir() {
        String path = cacheDir.getAbsolutePath();
        String substring = path.substring(0, path.lastIndexOf("/"));
        return new File(substring);
    }


}
