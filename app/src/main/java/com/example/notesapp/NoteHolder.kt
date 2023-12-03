package com.example.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ListItemNoteBinding
import java.text.SimpleDateFormat
import java.util.Locale

class NoteHolder(
    private val binding: ListItemNoteBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(note: Note, onNoteClicked: (noteId: Long) -> Unit) {
        val formattedDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = formattedDate.format(note.date).toString()

        binding.apply {
            noteTitleTextView.text = note.title
            noteTextView.text = note.text
            noteDateTextView.text = date

            root.setOnClickListener {
                onNoteClicked(note.id)
            }
        }
    }
}

class NoteListAdapter(
    private val notes: List<Note>,
    private val onNoteClicked: (noteId: Long) -> Unit
) : RecyclerView.Adapter<NoteHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNoteBinding.inflate(inflater, parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes[position]
        holder.onBind(note, onNoteClicked)
    }

    override fun getItemCount() = notes.size

}