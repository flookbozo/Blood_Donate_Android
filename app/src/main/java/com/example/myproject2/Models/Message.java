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

    @Expose
    @SerializedName("idUser")
    private int idUser;

    @Expose
    @SerializedName("messages")
    private String messages;

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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
