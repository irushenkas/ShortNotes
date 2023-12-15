package com.example.shortnotes.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShortNotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}