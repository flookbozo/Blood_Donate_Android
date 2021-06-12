package com.example.myproject2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LocationModel {

    @Expose
    @SerializedName("idUser")
    private int userid;

    @Expose
    @SerializedName("latitude")
    private double latitude;

    @Expose
    @SerializedName("longitude")
    private double longitude;

    @Expose
    @SerializedName("successes")
    private boolean successes;

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("messages")
    private String messages;

    @Expose
    @SerializedName("hospitalname")
    private String hospitalname;

    @Expose
    @SerializedName("idRequest")
    private int idRequest;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isSuccesses() {
        return successes;
    }

    public void setSuccesses(boolean successes) {
        this.successes = successes;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }
}
