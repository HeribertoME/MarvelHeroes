package com.hmelizarraraz.marvelheroes.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heriberto on 15/02/17.
 */

public class MarvelService {

    public static Marvel getMarvelAPI(){

        return new Retrofit.Builder()
                .baseUrl(Marvel.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Marvel.class);
    }
}
