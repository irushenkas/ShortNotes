package com.example.shortnotes.repository

import android.content.Context
import com.example.shortnotes.db.AppDb
import com.example.shortnotes.dto.Note
import com.example.shortnotes.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    context: Context,
) : NoteRepository {

    private val dao = AppDb.getInstance(context = context).noteDao()

    override fun getAll(): Flow<List<Note>> {
        return dao.getAll().map { item -> item.map { i->i.toDto() } }
    }

    override fun save(note: Note) {
        dao.insert(NoteEntity.fromDto(note))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }
}