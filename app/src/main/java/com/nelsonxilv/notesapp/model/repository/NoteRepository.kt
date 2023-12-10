package com.nelsonxilv.notesapp.model.repository

import android.content.Context
import androidx.room.Room
import com.nelsonxilv.notesapp.model.Note
import com.nelsonxilv.notesapp.model.database.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

private const val DATABASE_NAME = "note-database"

class NoteRepository @OptIn(DelicateCoroutinesApi::class)
private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {

    private val database: NoteDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            DATABASE_NAME
        )
        .build()

    fun getNotes(): Flow<List<Note>> =
        database.noteDao().getNotes()

    suspend fun getNote(id: Long): Note =
        database.noteDao().getNote(id)

    fun updateNote(note: Note) {
        coroutineScope.launch {
            database.noteDao().updateNote(note)
        }
    }

    suspend fun addNote(note: Note) =
        database.noteDao().addNote(note)

    suspend fun deleteNote(note: Note) =
        database.noteDao().deleteNote(note)

    companion object {

        private var INSTANCE: NoteRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NoteRepository(context)
            }
        }

        fun get(): NoteRepository {
            return INSTANCE ?: throw IllegalStateException("NoteRepository must be initialize")
        }
    }
}