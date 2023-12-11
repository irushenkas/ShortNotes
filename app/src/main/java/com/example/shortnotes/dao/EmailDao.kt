package com.example.shortnotes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shortnotes.entity.EmailEntity

@Dao
interface EmailDao {
    @Query("SELECT DISTINCT * FROM EmailEntity ORDER BY id DESC")
    fun getEmail(): EmailEntity

    @Insert
    fun insert(email: EmailEntity)

    @Query("DELETE FROM EmailEntity WHERE id = :id")
    fun removeById(id: Long)
}