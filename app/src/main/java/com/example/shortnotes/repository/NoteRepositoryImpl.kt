package com.example.shortnotes.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.shortnotes.db.AppDb
import com.example.shortnotes.dto.Note
import com.example.shortnotes.entity.NoteEntity

class NoteRepositoryImpl(
    context: Context,
) : NoteRepository {

    private val dao = AppDb.getInstance(context = context).noteDao()

    override fun getAll(): LiveData<List<Note>> {
        return dao.getAll().map { list ->
            list.map {
                it.toDto()
            }
        }
    }

    override fun save(note: Note) {
        dao.insert(NoteEntity.fromDto(note))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }
}