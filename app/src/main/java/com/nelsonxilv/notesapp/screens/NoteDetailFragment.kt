package com.nelsonxilv.notesapp.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nelsonxilv.notesapp.MainActivity
import com.nelsonxilv.notesapp.R
import com.nelsonxilv.notesapp.databinding.FragmentNoteDetailBinding
import kotlinx.coroutines.launch

class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding."
        }

    private val args: NoteDetailFragmentArgs by navArgs()

    private val noteDetailViewModel: NoteDetailViewModel by viewModels {
        NoteDetailViewModelFactory(args.noteId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentNoteDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        addMenuToolbar(menuHost)

        val toolbar = (activity as MainActivity?)?.supportActionBar
        setToolbar(toolbar)

        binding.apply {
            titleEditText.doOnTextChanged { text, _, _, _ ->
                noteDetailViewModel.updateNote { oldNote ->
                    oldNote.copy(title = text.toString())
                }
            }

            textEditText.doOnTextChanged { text, _, _, _ ->
                noteDetailViewModel.updateNote { oldNote ->
                    oldNote.copy(text = text.toString())
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
                menuInflater.inflate(R.menu.fragment_note_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.delete_note -> {
                        viewLifecycleOwner.lifecycleScope.launch {
                            noteDetailViewModel.note.value?.let {
                                noteDetailViewModel.deleteNote(it)
                            }
                        }
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setToolbar(toolbar: androidx.appcompat.app.ActionBar?) {
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setTitle(R.string.back)
        toolbar?.subtitle = null
    }

}