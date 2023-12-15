package com.example.shortnotes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shortnotes.dto.Note
import com.example.shortnotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    val data = noteRepository.getFlow()

    fun save(text: String, date: String) {
        noteRepository.save(Note(0, text, date))
    }

    fun removeById(id: Long) {
        noteRepository.removeById(id)
    }

    fun getAllDataForEmail(): List<Note> {
        return noteRepository.getAll()
    }
}