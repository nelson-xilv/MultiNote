package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class NotesListFragment : Fragment() {

    private val notesListViewModel: NotesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}