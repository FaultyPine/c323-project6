package com.example.project6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(val dao: NotesDao): ViewModel() {
    var newNoteName = ""
    var newNoteContent = ""
    val notes = dao.getAll()
    private val _navigateToNote = MutableLiveData<Long?>()
    val navigateToNote = _navigateToNote
    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.noteName = newNoteName
            note.noteContent = newNoteContent
            dao.insert(note)
        }
    }
    fun onNoteClicked(noteId: Long) {
        _navigateToNote.value = noteId
    }
    fun onNoteNavigated() {
        _navigateToNote.value = null
    }
}