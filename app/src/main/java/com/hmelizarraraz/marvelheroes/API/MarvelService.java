package com.hmelizarraraz.marvelheroes.API;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heriberto on 15/02/17.
 */

public class MarvelService {

    public static Marvel getMarvelAPI(){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request originalRequest = chain.request();
                        HttpUrl originalUrl = originalRequest.url();

                        HttpUrl finalUrl = originalUrl.newBuilder()
                                .addEncodedQueryParameter(Marvel.API_KEY_KEY, Marvel.API_KEY_VALUE)
                                .addEncodedQueryParameter(Marvel.TIME_STAMP_KEY, Marvel.TIME_STAMP_VALUE)
                                .addEncodedQueryParameter(Marvel.HASH_KEY, Marvel.HASH_VALUE)
                                .build();

                        Request.Builder requestBuilder = originalRequest.newBuilder().url(finalUrl);
                        Request finalRequest = requestBuilder.build();

                        return chain.proceed(finalRequest);

                    }
                }).build();

        return new Retrofit.Builder()
                .baseUrl(Marvel.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Marvel.class);
    }
}
