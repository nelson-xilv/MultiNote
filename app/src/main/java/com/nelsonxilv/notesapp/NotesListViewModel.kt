package com.nelsonxilv.notesapp

import androidx.lifecycle.ViewModel
import com.nelsonxilv.notesapp.model.Note
import java.util.Date

class NotesListViewModel : ViewModel() {

    val notes = mutableListOf<Note>()

    init {
        for (i in 0..10) {
            val note = Note(
                id = i.toLong(),
                title = "Note #$i",
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis a finibus orci. Quisque rhoncus tellus in tristique rhoncus. Etiam eu volutpat nulla. Maecenas et odio id quam cursus bibendum. Curabitur lorem ipsum, molestie in consectetur non, tempus ut urna. Integer viverra, erat eget elementum vehicula, urna metus rhoncus odio, id rhoncus nisl sapien a mi. In nec quam eget nulla porta tristique. Vivamus semper consectetur commodo. Aliquam in ornare dolor, nec convallis velit. Nunc a sagittis neque. Mauris dictum egestas lacus, a vehicula nisl vestibulum sit amet. Maecenas vel tortor eget quam sollicitudin laoreet eu non elit.",
                date = Date()
            )

            notes += note
        }
    }

}