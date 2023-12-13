package com.example.shortnotes.repository

import android.content.Context
import com.example.shortnotes.db.AppDb
import com.example.shortnotes.dto.Email
import com.example.shortnotes.entity.EmailEntity

class EmailRepositoryImpl(
    context: Context,
) : EmailRepository {

    private val dao = AppDb.getInstance(context = context).emailDao()

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