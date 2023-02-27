package com.example.membersofparliament_project

import MemberOfParliament
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliament_project.databinding.RecyclerviewItemBinding

class EveryoneAdapter(val members: List<MemberOfParliament>):RecyclerView.Adapter<EveryoneAdapter.EveryoneViewHolder>() {

    inner class EveryoneViewHolder(val itemBinding: RecyclerviewItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(members: MemberOfParliament){
            itemBinding.name.text = members.first + " " + members.last
            itemBinding.party.text = members.party
        }
        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Navigation.findNavController(itemView).navigate(R.id.action_showEveryone_to_singleMember)
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