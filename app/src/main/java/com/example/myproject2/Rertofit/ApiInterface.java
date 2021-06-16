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
    @POST("auth/user/register")
    Call<UserModel> registerUser(@Field("username") String username,
                                 @Field("password") String password,
                                 @Field("email") String email,
                                 @Field("prefix") String prefix,
                                 @Field("firstname") String name,
                                 @Field("lastname") String lastname,
                                 @Field("gender") String gender,
                                 @Field("duringpregnancy") String hiddenAnswer1,
                                 @Field("breastfeeding") String hiddenAnswer2,
                                 @Field("givebirth_past_6") String hiddenAnswer3,
                                 @Field("typeblood") String typeblood,
                                 @Field("typerh") String typerh,
                                 @Field("date") String birthday,
                                 @Field("question") String question,
                                 @Field("answer") String answer,
                                 @Field("phonnumber") String user_tel);

    @FormUrlEncoded
    @POST("auth/user/location")
    Call<LocationModel> locationUser(@Field("idUser") int idUser,
                                     @Field("latitude") double latitude,
                                     @Field("longitude") double longitude);


    @GET("edituser/{id}")
    Call<UserModel> getDataUser(@Path("id") int userid);

    @FormUrlEncoded
    @POST("updateProfile")
    Call<UserModel> updateDataUser(@Field("id") int userid,
                                   @Field("username") String username,
                                   @Field("email") String email,
                                   @Field("prefix") String prefix,
                                   @Field("firstname") String name,
                                   @Field("lastname") String lastname,
                                   @Field("gender") String gender,
                                   @Field("duringpregnancy") String hiddenAnswer1,
                                   @Field("breastfeeding") String hiddenAnswer2,
                                   @Field("givebirth_past_6") String hiddenAnswer3,
                                   @Field("typeblood") String typeblood,
                                   @Field("typerh") String typerh,
                                   @Field("date") String birthday,
                                   @Field("phonnumber") String user_tel);

    @FormUrlEncoded
    @POST("userreply")
    Call<Message> userReply(@Field("idRequest") int idRequest,
                            @Field("answer") int answer,
                            @Field("idUser") int idUser);
}
