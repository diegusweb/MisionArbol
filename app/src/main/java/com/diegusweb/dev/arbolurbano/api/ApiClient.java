package com.diegusweb.dev.misionarbol.api;

import com.diegusweb.dev.misionarbol.helper.InfoConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 17/01/2017.
 */

public class ApiClient {
;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(InfoConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
