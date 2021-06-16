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

    @Query("SELECT * FROM messageitem WHERE idUser = :idUser")
    List<Message> getMessageId(int idUser);

    @Insert
    void insertMessage(Message message);

    @Query("SELECT * FROM messageitem WHERE idUser = :idUser AND hosname LIKE :hosName")
    List<Message> findRequest(int idUser, String hosName);

    @Query("UPDATE messageitem SET idRequest = :idrequest, active = 1 WHERE idUser = :idUser AND hosname LIKE :hosName")
    void updateMessage(int idrequest, int idUser, String hosName);

    @Query("UPDATE messageitem SET active = 0 WHERE idUser = :idUser AND hosname LIKE :hosName")
    void updateActive(int idUser, String hosName);
}
