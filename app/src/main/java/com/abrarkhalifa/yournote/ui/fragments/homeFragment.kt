package com.abrarkhalifa.yournote.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.abrarkhalifa.yournote.Model.Notes
import com.abrarkhalifa.yournote.R
import com.abrarkhalifa.yournote.ViewModel.NotesViewModel
import com.abrarkhalifa.yournote.databinding.FragmentHomeBinding
import com.abrarkhalifa.yournote.ui.adapters.NotesAdapter

class homeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMYNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        viewModel.getAllNote().observe(viewLifecycleOwner) { notesList ->

            for (i in notesList) {
                oldMYNotes = notesList as ArrayList<Notes>
                binding.rcAllNotes.layoutManager = GridLayoutManager(context, 2)
                adapter = NotesAdapter(context, notesList)
                binding.rcAllNotes.adapter = adapter
            }

        }

        binding.viewAllNotes.setOnClickListener {
            viewModel.getAllNote().observe(viewLifecycleOwner) { notesList ->

                for (i in notesList) {
                    oldMYNotes = notesList as ArrayList<Notes>

                    binding.rcAllNotes.layoutManager = GridLayoutManager(context, 2)
                    adapter = NotesAdapter(context, notesList)
                    binding.rcAllNotes.adapter = adapter                }

            }
        }

        binding.filterHigh.setOnClickListener {

            viewModel.getHighNotes().observe(viewLifecycleOwner) { noteList ->
                for (i in noteList) {
                    oldMYNotes = noteList as ArrayList<Notes>

                    binding.rcAllNotes.layoutManager = GridLayoutManager(context, 2)
                    adapter = NotesAdapter(context, noteList)
                    binding.rcAllNotes.adapter = adapter                }
            }

        }

        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { noteList ->
                for (i in noteList) {
                    oldMYNotes = noteList as ArrayList<Notes>

                    binding.rcAllNotes.layoutManager = GridLayoutManager(context, 2)
                    adapter = NotesAdapter(context, noteList)
                    binding.rcAllNotes.adapter = adapter                }
            }

        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { noteList ->
                for (i in noteList) {
                    oldMYNotes = noteList as ArrayList<Notes>

                    binding.rcAllNotes.layoutManager = GridLayoutManager(context, 2)
                    adapter = NotesAdapter(context, noteList)
                    binding.rcAllNotes.adapter = adapter                }
            }

        }






        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search Your Note..."
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               NotesFiltring(newText)
                return true
            }

        })



        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltring(newText: String?) {

        var newFilterList = ArrayList<Notes>()
        for (i in oldMYNotes){
            if (i.title!!.contains(newText!!) || i.subtitle!!.contains(newText)){
                newFilterList.add(i)

            }
        }
        adapter.filtering(newFilterList)
    }


}