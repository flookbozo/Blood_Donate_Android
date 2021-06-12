package com.example.myproject2.Rertofit;

import com.example.myproject2.Models.LocationModel;
import com.example.myproject2.Models.Message;
import com.example.myproject2.Models.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("auth/user/login")
    Call<UserModel> loginUser(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("registeruser.php")
    Call<UserModel> registerUser(@Field("username") String username,
                                 @Field("password") String password,
                                 @Field("name") String name,
                                 @Field("user_tel") String user_tel);

    @FormUrlEncoded
    @POST("auth/user/location")
    Call<LocationModel> locationUser(@Field("idUser") int idUser,
                                     @Field("latitude") double latitude,
                                     @Field("longitude") double longitude);


    @GET("edituser/{id}")
    Call<UserModel> getDataUser(@Path("id") int userid);

    @FormUrlEncoded
    @POST("userreply")
    Call<Message> userReply(@Field("idRequest") int idRequest,
                            @Field("answer") int answer);
}
