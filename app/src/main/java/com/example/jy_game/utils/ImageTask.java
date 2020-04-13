package com.example.jy_game.utils;

import android.net.Uri;
import android.util.Log;

import com.example.jy_game.MyApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.example.jy_game.utils.Constants.IMGS_SP_KEY;

public class ImageTask implements Runnable {

    private static final String TAG = "ImageTask";

    String fileName;
    String url;

    public ImageTask(String fileName, String url) {

        this.fileName = fileName;
        this.url = url;


        Log.d(TAG, "ImageTask: " + url);
    }

    InputStream inputStream = null;
    FileOutputStream outputStream = null;
    File dirFile;

    @Override
    public void run() {


        try {
            String[] split = url.split("/");
            if (split.length > 3) {
                String dir = split[split.length - 2];
                dirFile = new File(MyApp.getFiledir() + File.separator +dir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
            }

            File file = new File(MyApp.getFiledir() + File.separator + dirFile.getName() + File.separator + fileName);

            if (!file.exists()){
                file.createNewFile();
            }
            String decodeUrl = Uri.encode(url);
            decodeUrl = decodeUrl.replace("%3A", ":");
            decodeUrl = decodeUrl.replace("%2F", "/");

            Log.d(TAG, "run:decodeUrl= " + decodeUrl);
            HttpURLConnection connection = getConnection(decodeUrl);

            inputStream = connection.getInputStream();

            outputStream = new FileOutputStream(file);

            byte[] bytes = new byte[4 * 1024];
            int readLength;

            while ((readLength = inputStream.read(bytes)) != -1) {

                outputStream.write(bytes, 0, readLength);

            }
            String imgUrls = SpUtils.getInstance().getString(IMGS_SP_KEY);
            imgUrls = imgUrls+fileName+",";
            SpUtils.getInstance().setValue(IMGS_SP_KEY, imgUrls);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (outputStream != null) {

                    outputStream.close();
                }
                if (inputStream != null) {

                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public HttpURLConnection getConnection(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        return conn;
    }
}
