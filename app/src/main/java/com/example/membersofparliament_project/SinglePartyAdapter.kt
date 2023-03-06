package com.example.membersofparliament_project

import MemberOfParliament
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliament_project.databinding.RecyclerviewItemBinding

class SinglePartyAdapter(val members: List<String>):RecyclerView.Adapter<SinglePartyAdapter.SinglePartyViewHolder>() {

    inner class SinglePartyViewHolder(val itemBinding: RecyclerviewItemBinding):RecyclerView.ViewHolder(itemBinding.root){

       fun bindItem(members: String){
           itemBinding.name.text = members
       }
        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val action = showPartyDirections.actionShowPartyToSingleMember2(position.toString())
                Navigation.findNavController(itemView).navigate(action)
            }
        }
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePartyViewHolder {
        return SinglePartyViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: SinglePartyViewHolder, position: Int) {
        val item = members[position]
        holder.bindItem(item)
    }
}
