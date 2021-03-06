package com.diegusweb.dev.misionarbol.api;

import com.diegusweb.dev.misionarbol.models.GithubUser;
import com.diegusweb.dev.misionarbol.models.InfoUser;
import com.diegusweb.dev.misionarbol.models.Login;
import com.diegusweb.dev.misionarbol.models.PointsTree;
import com.diegusweb.dev.misionarbol.models.ServerResponse;
import com.diegusweb.dev.misionarbol.models.TestItems;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
    Call<Login> authenticate(@Query("email") String email,
                             @Query("password") String password);

    //@FormUrlEncoded
    @GET("v1/authenticate/user")
    Call<InfoUser> getInfoUser(@Query("token") String token);

    //@Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("v1/items/getuser")
    Call<InfoUser> getInfoUserTest(@Query("email") String email);

    //Test
    @GET("/users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);
	
	@POST("v1/authenticate/signup")
    Call<Login> signup(@Query("first_name") String first_name,
                       @Query("last_name") String last_name,
                       @Query("password") String password,
                       @Query("email") String email);

   // @Multipart
    @POST("v1/points")
    Call<ServerResponse> uploadFile_(@Query("title") String title,
                                    @Query("type_id") int type_id,
                                    @Query("user_id") int user_id,
                                    @Query("description") String description,
                                    @Query("status") int status,
                                    @Query("lat") Double lat,
                                    @Query("lng") Double lng,
                                    @Query("country") String country,
                                    @Query("city") String city);

    //Call<ServerResponse> uploadFile(@Part MultipartBody.Part file,
      //                              @Part("file") RequestBody name);

    @GET("v1/points")
    Call<List<PointsTree>> getPointTree();

    @GET("v1/tree")
    Call<List<Tree>> getTreeLibrary();

    @GET("v1/tree/{id}")
    Call<Tree> getTreeId(@Path("id") int id);

    @Multipart
    @POST("v1/points")
    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file,
                                    @Part("file") RequestBody name,
                                    @Query("title") String title,
                                    @Query("type_id") int type_id,
                                    @Query("user_id") int user_id,
                                    @Query("description") String description,
                                    @Query("status") int status,
                                    @Query("lat") Double lat,
                                    @Query("lng") Double lng,
                                    @Query("country") String country,
                                    @Query("city") String city);


    //Mis reportes
    @GET("v1/points/{id}")
    Call<List<PointsTree>> getReportPoints(@Path("id") int id);
}
