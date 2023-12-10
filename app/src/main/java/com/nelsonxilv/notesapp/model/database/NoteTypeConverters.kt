package com.nelsonxilv.notesapp.model.database

import androidx.room.TypeConverter
import java.util.Date

class NoteTypeConverters {
    @TypeConverter
    fun formatDate(date: Date): Long = date.time

    @TypeConverter
    fun toDate(millis: Long): Date = Date(millis)
}