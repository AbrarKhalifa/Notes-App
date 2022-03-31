package com.abrarkhalifa.yournote.ui.fragments

import android.os.Binder
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abrarkhalifa.yournote.Model.Notes
import com.abrarkhalifa.yournote.R
import com.abrarkhalifa.yournote.ViewModel.NotesViewModel
import com.abrarkhalifa.yournote.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class editNotesFragment : Fragment() {

    val oldNotes by navArgs<editNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority:String = "1"

    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubtitle.setText(oldNotes.data.subtitle)
        binding.edtNotes.setText(oldNotes.data.notes)


        // this is for show priority
        when(oldNotes.data.priority){
            "1"->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.done_)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)

            }
            "2"->{
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.done_)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)

            }
            "3"->{
                priority = "3"
                binding.pRed.setImageResource(R.drawable.done_)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)

            }
        }

        // this is for changing button
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

        binding.btnSaveNotes.setOnClickListener {
            updateNotes(it)
        }



        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title  = binding.edtTitle.text.toString()
        val subtitle  = binding.edtSubtitle.text.toString()
        val notes  = binding.edtNotes.text.toString()

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val notesDate = sdf.format(Date())

        val data = Notes(oldNotes.data.id,title,subtitle,notes,notesDate,priority)
        viewModel.updateAllNote(data)

        Toast.makeText(context, "Notes update successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){

            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)
            bottomSheet.setCancelable(false)

            val no = bottomSheet.findViewById<TextView>(R.id.delete_no)
            val yes = bottomSheet.findViewById<TextView>(R.id.delete_yes)
            no?.setOnClickListener {
                bottomSheet.dismiss()
            }
            yes?.setOnClickListener {
               viewModel.deleteAllNote(oldNotes.data.id!!)
                bottomSheet.dismiss()
            }


            bottomSheet.show()

        }
        return super.onOptionsItemSelected(item)
    }

}