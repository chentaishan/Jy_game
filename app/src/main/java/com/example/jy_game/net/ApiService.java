package com.example.jy_game.net;

import com.example.jy_game.TranslationBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String baseUrl = "http://fy.iciba.com/";

    @GET("ajax.php?a=fy&f=auto&t=auto")
    Call<TranslationBean> getZhBean(@Query("w") String word);
}
