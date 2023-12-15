package com.example.shortnotes.dao

import com.example.shortnotes.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
    @Provides
    fun provideNoteDao(db: AppDb): NoteDao = db.noteDao()

    @Provides
    fun provideEmailDao(db: AppDb): EmailDao = db.emailDao()
}