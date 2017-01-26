package com.diegusweb.dev.misionarbol.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 17/01/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "http://10.0.0.7:8075/2017/laravel/misionarbol/public/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
