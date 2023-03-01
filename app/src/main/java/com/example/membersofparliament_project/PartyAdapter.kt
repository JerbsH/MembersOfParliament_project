package com.example.membersofparliament_project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.navArgument
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliament_project.databinding.RecyclerviewItemBinding

class PartyAdapter(val parties: List<String>):RecyclerView.Adapter<PartyAdapter.PartyViewHolder>() {

    inner class PartyViewHolder(val itemBinding: RecyclerviewItemBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bindItem(String: String){
            itemBinding.name.text = parties[position]
        }
        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val action = SelectPartyDirections.actionSelectPartyToShowParty(parties[position])
                Log.d("bug", parties[position])
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
