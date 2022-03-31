package com.abrarkhalifa.yournote.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.abrarkhalifa.yournote.Database.NotesDatabase
import com.abrarkhalifa.yournote.Model.Notes
import com.abrarkhalifa.yournote.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository : NotesRepository

    init {

        val dao = NotesDatabase.getDatabaseInstance(application).myNoteDao()
        repository = NotesRepository(dao)

    }

    fun getAllNote():LiveData<List<Notes>> = repository.getAllNotes()

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun deleteAllNote(id:Int){
        repository.deleteNotes(id)
    }

    fun updateAllNote(notes: Notes){
        repository.updateNotes(notes)
    }

    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()

    fun getMediumNotes():LiveData<List<Notes>> =repository.getMediumNotes()

    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()



}