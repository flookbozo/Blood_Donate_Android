package com.example.myproject2.Models;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Path;
import retrofit2.http.Query;

public class UserModel {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("firstname")
    private String name;

    @Expose
    @SerializedName("phonnumber")
    private String user_tel;

    @Expose
    @SerializedName("gender")
    private String user_gender;

    @Expose
    @SerializedName("typeblood")
    private String user_bloodtype;

    @Expose
    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel(){

    }


    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_bloodtype() {
        return user_bloodtype;
    }

    public void setUser_bloodtype(String user_bloodtype) {
        this.user_bloodtype = user_bloodtype;
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_tel() {
        return user_tel;
    }
}
