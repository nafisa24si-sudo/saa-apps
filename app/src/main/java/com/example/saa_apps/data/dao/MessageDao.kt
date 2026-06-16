package com.example.saa_apps.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.saa_apps.data.entity.MessageEntity

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY createdAt DESC")
    suspend fun getAll(): List<MessageEntity>

    @Insert
    suspend fun insert(message: MessageEntity)
}