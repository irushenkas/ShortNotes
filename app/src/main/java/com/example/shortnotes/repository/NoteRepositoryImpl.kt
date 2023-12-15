package com.example.shortnotes.repository

import com.example.shortnotes.dao.NoteDao
import com.example.shortnotes.dto.Note
import com.example.shortnotes.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {

    override fun getFlow(): Flow<List<Note>> {
        return dao.getFlow().map { item -> item.map { i->i.toDto() } }
    }

    override fun getAll(): List<Note> {
        return dao.getAll().map { item -> item.toDto() }
    }

    override fun save(note: Note) {
        dao.insert(NoteEntity.fromDto(note))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }
}