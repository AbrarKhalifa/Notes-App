package com.abrarkhalifa.yournote.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.abrarkhalifa.yournote.Model.Notes
import com.abrarkhalifa.yournote.R
import com.abrarkhalifa.yournote.ViewModel.NotesViewModel
import com.abrarkhalifa.yournote.databinding.FragmentCreateNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class createNoteFragment: Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var priority:String = "1"
    val viewModel:NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)

        binding.btnSaveNotes.setOnClickListener{
            saveNote(it)
        }

        binding.pGreen.setImageResource(R.drawable.done_)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.done_)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.done_)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.done_)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }


        return binding.root
    }


    private fun saveNote(it: View?) {

        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSubtitle.text.toString()
        val note = binding.edtNotes.text.toString()

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val notesDate = sdf.format(Date())

        val data = Notes(null,
        title = title,
        subtitle = subtitle,
        notes = note,
        date = notesDate,
        priority = priority)

        viewModel.addNotes(data)

       Toast.makeText(context, "notes created successfully", Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)


    }
}