package com.example.project6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.project6.databinding.FragmentNoteViewBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [NoteViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteViewFragment : Fragment() {
    private lateinit var binding: FragmentNoteViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteViewBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = NotesViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)

        binding.bAddNote.setOnClickListener {
            viewModel.addNote()
        }

        val adapter = ItemAdapter(
            { noteId: Long -> viewModel.onNoteClicked(noteId) },
            {noteId: Long -> viewModel.deleteNote(noteId) }
        )

        return view
    }
}