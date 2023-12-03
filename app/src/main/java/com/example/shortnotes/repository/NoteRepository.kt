package com.example.shortnotes.repository

import com.example.shortnotes.dto.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getFlow(): Flow<List<Note>>
    fun getAll(): List<Note>
    fun save(note: Note)
    fun removeById(id: Long)
}