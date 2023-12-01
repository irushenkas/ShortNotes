package com.example.shortnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.shortnotes.dto.Note
import com.example.shortnotes.repository.NoteRepository
import com.example.shortnotes.repository.NoteRepositoryImpl

private val empty = Note(
    id = 0,
    text = "",
    date = ""
)

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository = NoteRepositoryImpl(application)

    val data = noteRepository.getAll()
    private val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            noteRepository.save(it)
        }
        edited.value = empty
    }

    fun removeById(id: Long) {
        noteRepository.removeById(id)
    }
}