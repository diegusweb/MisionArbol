package com.diegusweb.dev.misionarbol.api;

import com.diegusweb.dev.misionarbol.models.TestItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HP on 17/01/2017.
 */

public interface ApiInterface {
    @GET("/search/users")
    Call<TestItems> getUsersNamedTom(@Query("q") String name);

    @GET("v1/items")
    Call<List<TestItems>> getListTestItems();


}
