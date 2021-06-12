package com.example.myproject2.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "messageitem")
public class Message {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "hosname")
    public String hosname;

    @Expose
    @SerializedName("idRequest")
    @ColumnInfo(name = "idRequest")
    public int idRequest;

    @ColumnInfo(name = "active")
    public int active;

    @Expose
    @SerializedName("success")
    private boolean success;

    @Expose
    @SerializedName("answer")
    public int answer;

    public Message( String hosname, int idRequest) {
        this.hosname = hosname;
        this.idRequest = idRequest;
        this.active = 1;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
