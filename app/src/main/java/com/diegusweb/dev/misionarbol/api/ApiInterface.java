package com.diegusweb.dev.misionarbol.api;

import com.diegusweb.dev.misionarbol.models.GithubUser;
import com.diegusweb.dev.misionarbol.models.InfoUser;
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
    /*@GET("/search/users")
    Call<TestItems> getUsersNamedTom(@Query("q") String name);*/

    @GET("v1/items")
    Call<List<TestItems>> getListTestItems();

    @GET("v1/authenticate")
    Call<Login> authenticate(@Query("email") String email, @Query("password") String password);

    //@FormUrlEncoded
    @GET("v1/authenticate/user")
    Call<InfoUser> getInfoUser(@Query("token") String token);

    //@Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("v1/items/getuser")
    Call<InfoUser> getInfoUserTest(@Query("email") String email);

    //Test
    @GET("/users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);
	
	@POST("api/{email}/{password}")
    Call<Login> registration(@Path("email") String email, @Path("password") String password);

}
