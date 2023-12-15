package com.example.shortnotes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shortnotes.dao.EmailDao
import com.example.shortnotes.dao.NoteDao
import com.example.shortnotes.entity.EmailEntity
import com.example.shortnotes.entity.NoteEntity

@Database(entities = [NoteEntity::class, EmailEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun emailDao(): EmailDao
}