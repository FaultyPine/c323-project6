package com.example.project6

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

class EditNoteViewModel(noteId: Long, val dao: NotesDao): ViewModel() {
    val note = dao.get(noteId)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList = _navigateToList
    fun updateNote() {
        viewModelScope.launch {
            Log.d("NOTE", "Note ID: ${note.value!!.noteId}, Note Name: ${note.value!!.noteName}")
            dao.update(note.value!!)
            Log.d("NOTE", "Note ID: ${note.value!!.noteId}, Note Name: ${note.value!!.noteName}")
            _navigateToList.value = true
        }
    }
    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}