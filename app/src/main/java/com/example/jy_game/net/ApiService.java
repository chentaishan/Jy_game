package com.example.jy_game.net;

import com.example.jy_game.TranslationBean;
import com.example.jy_game.bean.Imagebean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("ajax.php?a=fy&f=auto&t=auto")
    Call<TranslationBean> getZhBean(@Query("w") String word);



    @GET("games/img/getImgList")
    Call<Imagebean> getImages( );
}
