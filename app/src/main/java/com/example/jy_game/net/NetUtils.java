package com.example.jy_game.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.jy_game.MyApp;
import com.example.jy_game.TranslationBean;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {

    private static final String TAG = "NetUtils";
    public static void  getNetData( String name, final CallBack callBack){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new NetWorkInterceptor())
                .cache(new Cache(MyApp.context.getCacheDir(),1024*1024*10)).build();

        Log.d(TAG, "getNetData: "+name);
        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.baseUrl).build();
        final ApiService apiService = retrofit.create(ApiService.class);
        apiService.getZhBean(name).enqueue(new Callback<TranslationBean>() {
            @Override
            public void onResponse(Call<TranslationBean> call, Response<TranslationBean> response) {
                final TranslationBean.ContentBean content = response.body().getContent();
                final List<String> word_mean = content.getWord_mean();
                for (int i = 0; i < word_mean.size(); i++) {
                    String name = word_mean.get(i);
                    Log.d(TAG, "onResponse: 翻译内容="+name);
                    if (name.startsWith("n. ")){

//                        "n. 天空;空气;空中;神态;曲调;",
//                        n. 鸟，禽;
                        String[] n = name.split(";");
                        if (n.length>0){
                            String first = n[0].substring(3);
                            if (first.contains("<美>")){
                                first= first.replace("<美>","");
                            }
                            if (first.contains("，")){
                                first = first.split("，")[0];
                            }
                            callBack.updateSuccess(first);
//                            mName.setText(first);
//                            talkSound(first);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<TranslationBean> call, Throwable t) {

                Log.d(TAG, "onFailure: "+t.getMessage());
                callBack.updateFailed(t.getMessage());
            }
        });

    }
    static class NetWorkInterceptor implements Interceptor {
        Request request;
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            request = chain.request();
            if (! checkNetWork()){

                request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            okhttp3.Response response = chain.proceed(request);
            if (! checkNetWork()){

                int maxAge=0;
                return  response.newBuilder().removeHeader("Pragma").header("Cache-Control","public ,max-age="+maxAge).build();

            }else{

                int maxTime = 60*60 *24;

                return  response.newBuilder().removeHeader("Pragma").header("Cache-Control","public ,max-age="+maxTime).build();

            }


        }
    }

    /**
     * 检查是否有网络
     * @return
     */
    public static boolean checkNetWork(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null;
    }
}
