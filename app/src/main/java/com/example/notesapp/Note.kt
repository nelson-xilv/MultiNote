package com.example.notesapp

import java.util.Date

data class Note(
    val id: Long,
    val name: String,
    val title: String,
    val date: Date
)