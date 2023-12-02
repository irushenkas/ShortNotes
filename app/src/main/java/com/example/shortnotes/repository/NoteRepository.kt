package com.example.shortnotes.repository

import com.example.shortnotes.dto.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAll(): Flow<List<Note>>
    fun save(note: Note)
    fun removeById(id: Long)
}