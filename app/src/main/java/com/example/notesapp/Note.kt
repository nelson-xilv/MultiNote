package com.example.notesapp

import java.util.Date

data class Note(
    val id: Long,
    val title: String,
    val text: String,
    val date: Date
)