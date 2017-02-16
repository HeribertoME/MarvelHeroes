package com.hmelizarraraz.marvelheroes.API;

import com.hmelizarraraz.marvelheroes.Models.Basic;
import com.hmelizarraraz.marvelheroes.Models.Data;
import com.hmelizarraraz.marvelheroes.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by heriberto on 15/02/17.
 */

public interface Marvel {

    String BASE_URL = "http://gateway.marvel.com/";

    String API_KEY_KEY = "apikey";
    String API_KEY_VALUE = "180c59c14e5357d786951a00c07ad646";

    String TIME_STAMP_KEY = "ts";
    String TIME_STAMP_VALUE = "1";

    String HASH_KEY = "hash";
    String HASH_VALUE = "b041fe2872a01077a91fe058dd819b58";

    @GET("v1/public/series/{seriesId}/characters")
    Call<Basic<Data<ArrayList<SuperHero>>>> getHeroes(@Path("seriesId") int seriesId);

}
