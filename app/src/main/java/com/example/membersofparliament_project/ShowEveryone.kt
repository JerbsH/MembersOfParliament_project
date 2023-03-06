package com.example.membersofparliament_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.membersofparliament_project.databinding.FragmentShowEveryoneBinding

class ShowEveryone : Fragment() {
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * This view lists every member in a recyclerview with the option to click on a name and then show more info about that member.
    */
    private lateinit var binding: FragmentShowEveryoneBinding
    private lateinit var viewModel: EveryoneListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = EveryoneListViewModel()
        binding = FragmentShowEveryoneBinding.inflate(layoutInflater)
        viewModel.members.observe(viewLifecycleOwner){
            binding.everyoneRV.adapter = EveryoneAdapter(it)
        }
        return binding.root
    }
}
class EveryoneListViewModel: ViewModel(){
    var members: LiveData<List<String>> = Transformations.map(MemberRepo.logData){
        it.map { "${it.firstname} ${it.lastname} Party: ${it.party} Id:${it.hetekaId}" }.toSortedSet().toList()
    }
}