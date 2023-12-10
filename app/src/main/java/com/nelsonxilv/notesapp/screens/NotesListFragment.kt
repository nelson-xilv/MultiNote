package com.nelsonxilv.notesapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nelsonxilv.notesapp.NoteListAdapter
import com.nelsonxilv.notesapp.R
import com.nelsonxilv.notesapp.databinding.FragmentNotesListBinding
import kotlinx.coroutines.launch

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

        val menuHost: MenuHost = requireActivity()
        addMenuToolbar(menuHost)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                notesListViewModel.notes.collect { notes ->
                    binding.notesRecyclerView.adapter = NoteListAdapter(notes) { noteId ->
                        findNavController().navigate(
                            NotesListFragmentDirections.showNoteDetail(noteId)
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addMenuToolbar(menuHost: MenuHost) {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_notes_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_profile_image -> {
                        Toast.makeText(
                            context,
                            "Profile image clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}