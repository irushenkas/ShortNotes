package com.example.shortnotes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shortnotes.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity ORDER BY id DESC")
    fun getFlow(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity ORDER BY id DESC")
    fun getAll(): List<NoteEntity>

    @Insert
    fun insert(note: NoteEntity)

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    fun removeById(id: Long)
}