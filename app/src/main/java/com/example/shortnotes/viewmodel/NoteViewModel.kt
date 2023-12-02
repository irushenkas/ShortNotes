package com.example.shortnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shortnotes.dto.Note
import com.example.shortnotes.repository.NoteRepository
import com.example.shortnotes.repository.NoteRepositoryImpl

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository = NoteRepositoryImpl(application)

    val data = noteRepository.getAll()

    fun save(text: String, date: String) {
        noteRepository.save(Note(0, text, date))
    }

    fun removeById(id: Long) {
        noteRepository.removeById(id)
    }
}