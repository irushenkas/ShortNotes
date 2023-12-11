package com.example.shortnotes.repository

import com.example.shortnotes.dto.Email

interface EmailRepository {
    fun get(): Email
    fun save(email: Email)
    fun removeById(id: Long)
}