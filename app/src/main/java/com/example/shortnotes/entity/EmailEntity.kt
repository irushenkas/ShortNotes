package com.example.shortnotes.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shortnotes.dto.Email

@Entity
data class EmailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
) {
    fun toDto() = Email(id, name)

    companion object {
        fun fromDto(dto: Email) =
            EmailEntity(dto.id, dto.name)
    }
}