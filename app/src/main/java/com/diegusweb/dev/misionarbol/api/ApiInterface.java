package com.diegusweb.dev.misionarbol.api;

import com.diegusweb.dev.misionarbol.models.Login;
import com.diegusweb.dev.misionarbol.models.TestItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HP on 17/01/2017.
 */

public interface ApiInterface {
    @GET("/search/users")
    Call<TestItems> getUsersNamedTom(@Query("q") String name);

    @GET("v1/items")
    Call<List<TestItems>> getListTestItems();

    @FormUrlEncoded
    @POST("v1/authenticate")
    Call<Login> authenticate(@Field("email") String email, @Field("password") String password);

    /*@POST("/user/create")
    Call<Item> createUser(@Body String name, @Body String email);

    @PUT("/user/{id}/update")
    Call<Item> updateUser(@Path("id") String id , @Body Item user);*/

}
