package com.example.membersofparliament_project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliament_project.databinding.RecyclerviewItemBinding

class PartyAdapter(val parties: List<String>):RecyclerView.Adapter<PartyAdapter.PartyViewHolder>() {
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * Adapter for party list recyclerview.
    *
    */
    inner class PartyViewHolder(val itemBinding: RecyclerviewItemBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bindItem(members: String){
            itemBinding.name.text = members
        }

        /*
        * Creates an OnClickListener for single item.
        * gets position of clicked item and send it to the action as an argument
        */
        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val action = SelectPartyDirections.actionSelectPartyToShowParty(parties[position])
                Log.d("test", parties[position])
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        return PartyViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return parties.size
    }

    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        val item = parties[position]
        holder.bindItem(item)
    }
}
