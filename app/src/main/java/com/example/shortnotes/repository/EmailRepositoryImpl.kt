package com.example.shortnotes.repository

import com.example.shortnotes.dao.EmailDao
import com.example.shortnotes.dto.Email
import com.example.shortnotes.entity.EmailEntity
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val dao: EmailDao
) : EmailRepository {

    override fun get(): Email? {
        val email = dao.getEmail()
        return if(email != null) {
            dao.getEmail().toDto()
        } else {
            null
        }
    }

    override fun save(email: Email) {
        dao.insert(EmailEntity.fromDto(email))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }
}