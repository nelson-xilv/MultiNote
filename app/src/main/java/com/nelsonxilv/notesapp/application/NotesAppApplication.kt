package com.nelsonxilv.notesapp.application

import android.app.Application
import com.nelsonxilv.notesapp.model.repository.NoteRepository

class NotesAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteRepository.initialize(this)
    }
}