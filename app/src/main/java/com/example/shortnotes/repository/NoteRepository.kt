package com.example.shortnotes.repository

import androidx.lifecycle.LiveData
import com.example.shortnotes.dto.Note

interface NoteRepository {
    fun getAll(): LiveData<List<Note>>
    fun save(schedule: Note)
    fun removeById(id: Long)
}