package com.example.shortnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shortnotes.dto.Email
import com.example.shortnotes.repository.EmailRepository
import com.example.shortnotes.repository.EmailRepositoryImpl

class EmailViewModel(application: Application) : AndroidViewModel(application) {
    private val emailRepository: EmailRepository = EmailRepositoryImpl(application)

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