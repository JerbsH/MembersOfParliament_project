package com.example.membersofparliament_project

import MemberOfParliament
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliament_project.databinding.RecyclerviewItemBinding

class EveryoneAdapter(val members: List<String>):RecyclerView.Adapter<EveryoneAdapter.EveryoneViewHolder>() {

    inner class EveryoneViewHolder(val itemBinding: RecyclerviewItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(members: String){
            itemBinding.name.text = members
        }

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val selected = members[position]
                val action = ShowEveryoneDirections.actionShowEveryoneToSingleMember(selected)
                Log.d("test", selected)
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EveryoneViewHolder {
        return EveryoneViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: EveryoneViewHolder, position: Int) {
        val item = members[position]
        holder.bindItem(item)

    }
}