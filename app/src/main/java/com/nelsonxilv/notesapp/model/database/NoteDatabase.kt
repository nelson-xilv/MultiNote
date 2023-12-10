package com.nelsonxilv.notesapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nelsonxilv.notesapp.model.Note

@Database(entities = [Note::class], version = 1)
@TypeConverters(NoteTypeConverters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}