package com.example.project6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.project6.databinding.FragmentNoteBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        val noteId = NoteFragmentArgs.fromBundle(requireArguments()).noteId

        val application = requireNotNull(this.activity).application
        val dao = NotesDatabase.getInstance(application).notesDao

        val viewModelFactory = EditNoteViewModelFactory(noteId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[EditNoteViewModel::class.java]

        return view
    }

}