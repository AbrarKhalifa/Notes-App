package com.abrarkhalifa.yournote.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abrarkhalifa.yournote.Model.Notes
import com.abrarkhalifa.yournote.R
import com.abrarkhalifa.yournote.databinding.ItemsNotesBinding
import com.abrarkhalifa.yournote.ui.fragments.homeFragmentDirections

class NotesAdapter(val context: Context?, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesviewHolder>() {


    fun filtering(newFilterList: ArrayList<Notes>) {
        notesList = newFilterList
        notifyDataSetChanged()
    }


    class notesviewHolder(val binding: ItemsNotesBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesviewHolder {
        return notesviewHolder(
            ItemsNotesBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesviewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitles.text = data.title
        holder.binding.notesSubTitles.text = data.subtitle
        holder.binding.notesDates.text = data.date

        when (data.priority) {
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)

            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)

            }
        }


        holder.binding.root.setOnClickListener {
            val action = homeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }


}