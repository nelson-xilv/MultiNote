package com.nelsonxilv.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Note(
    @PrimaryKey val id: Long,
    val title: String,
    val text: String,
    val date: Date
)