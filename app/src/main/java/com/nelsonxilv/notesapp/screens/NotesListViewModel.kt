package com.nelsonxilv.notesapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nelsonxilv.notesapp.model.Note
import com.nelsonxilv.notesapp.model.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesListViewModel : ViewModel() {

    private val noteRepository = NoteRepository.get()

    private val _notes: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notes: StateFlow<List<Note>>
        get() = _notes.asStateFlow()

    init {
        viewModelScope.launch {
            noteRepository.getNotes().collect {
                _notes.value = it
            }
        }
    }

    suspend fun addNote(note: Note) =
        noteRepository.addNote(note)

    suspend fun deleteNote(note: Note) =
        noteRepository.deleteNote(note)
}