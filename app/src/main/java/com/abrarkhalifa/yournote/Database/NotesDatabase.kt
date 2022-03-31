package com.abrarkhalifa.yournote.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abrarkhalifa.yournote.Dao.NoteDao
import com.abrarkhalifa.yournote.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase :RoomDatabase(){

    // this fun for fetching data from the notedao class
    abstract fun myNoteDao():NoteDao

    companion object {

        @Volatile
        var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance

                return roomDatabaseInstance

            }

        }


    }

}