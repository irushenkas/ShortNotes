package com.example.shortnotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shortnotes.dao.EmailDao
import com.example.shortnotes.dao.NoteDao
import com.example.shortnotes.entity.EmailEntity
import com.example.shortnotes.entity.NoteEntity


@Database(entities = [NoteEntity::class, EmailEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    abstract fun emailDao(): EmailDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}