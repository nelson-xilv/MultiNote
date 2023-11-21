package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment() {

    private var _binding: FragmentNotesListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding it is null."
        }

    private val notesListViewModel: NotesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)

        binding.notesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notes = notesListViewModel.notes
        binding.notesRecyclerView.adapter = NoteListAdapter(notes) {
            Toast.makeText(
                context,
                "Note clicked",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}