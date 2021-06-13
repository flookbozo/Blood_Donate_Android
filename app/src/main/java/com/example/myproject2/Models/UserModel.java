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

    @Expose
    @SerializedName("prefix")
    private String prefix;

    @Expose
    @SerializedName("lastname")
    private String lastname;

    @Expose
    @SerializedName("duringpregnancy")
    private String duringpregnancy;

    @Expose
    @SerializedName("breastfeeding")
    private String breastfeeding;

    @Expose
    @SerializedName("givebirth_past_6")
    private String givebirth_past_6;

    @Expose
    @SerializedName("typerh")
    private String typerh;

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("question")
    private String question;

    @Expose
    @SerializedName("answer")
    private String answer;

    @Expose
    @SerializedName("message")
    private String message;


    public UserModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDuringpregnancy() {
        return duringpregnancy;
    }

    public void setDuringpregnancy(String duringpregnancy) {
        this.duringpregnancy = duringpregnancy;
    }

    public String getBreastfeeding() {
        return breastfeeding;
    }

    public void setBreastfeeding(String breastfeeding) {
        this.breastfeeding = breastfeeding;
    }

    public String getGivebirth_past_6() {
        return givebirth_past_6;
    }

    public void setGivebirth_past_6(String givebirth_past_6) {
        this.givebirth_past_6 = givebirth_past_6;
    }

    public String getTyperh() {
        return typerh;
    }

    public void setTyperh(String typerh) {
        this.typerh = typerh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
