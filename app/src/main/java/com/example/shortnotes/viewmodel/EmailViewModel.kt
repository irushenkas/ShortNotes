package com.example.shortnotes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shortnotes.dto.Email
import com.example.shortnotes.repository.EmailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val emailRepository: EmailRepository
): ViewModel() {

    fun save(name: String) {
        emailRepository.save(Email(0, name))
    }

    fun removeById(id: Long) {
        emailRepository.removeById(id)
    }

    fun getEmail(): Email? {
        return emailRepository.get()
    }
}