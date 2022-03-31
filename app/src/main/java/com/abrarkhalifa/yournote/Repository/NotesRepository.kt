package com.abrarkhalifa.yournote.Repository

import androidx.lifecycle.LiveData
import com.abrarkhalifa.yournote.Dao.NoteDao
import com.abrarkhalifa.yournote.Model.Notes

class NotesRepository(val dao: NoteDao) {

    fun getAllNotes():LiveData<List<Notes>> = dao.getNotes()

    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()

    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()

    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()

    fun insertNotes(notes: Notes){
        dao.inserNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

}