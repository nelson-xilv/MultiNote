package com.example.notesapp

import androidx.lifecycle.ViewModel
import java.util.Date

class NotesListViewModel : ViewModel() {

    val notes = mutableListOf<Note>()

    init {
        for (i in 0..10) {
            val note = Note(
                id = i.toLong(),
                name = "Note #$i",
                title = "Something text",
                date = Date()
            )

            notes += note
        }
    }

}