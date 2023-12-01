package com.example.shortnotes.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shortnotes.dto.Note

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val text: String,
    val date: String,
) {
    fun toDto() = Note(id, text, date)

    companion object {
        fun fromDto(dto: Note) =
            NoteEntity(dto.id, dto.text, dto.date)
    }
}