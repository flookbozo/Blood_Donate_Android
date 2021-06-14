package com.example.myproject2.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject2.Models.Message;

import java.util.List;

@Dao
public interface MessageDAO {

    @Query("SELECT * FROM messageitem")
    List<Message> getAllMessage();

    @Insert
    void insertMessage(Message message);

    @Query("SELECT * FROM messageitem WHERE hosname LIKE :hosName")
    List<Message> findRequest(String hosName);

    @Query("UPDATE messageitem SET idRequest = :idrequest, active = 1 WHERE hosname LIKE :hosName")
    void updateMessage(int idrequest, String hosName);

    @Query("UPDATE messageitem SET active = 0 WHERE hosname LIKE :hosName")
    void updateActive(String hosName);
}
