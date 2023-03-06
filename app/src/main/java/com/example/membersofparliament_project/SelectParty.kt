package com.example.membersofparliament_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.membersofparliament_project.databinding.FragmentSelectPartyBinding

class SelectParty : Fragment() {
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * Party selection fragment with recyclerview.
    * Lists all parties found in the database.
    * Items are clickable.
    */
    private lateinit var viewModel: SelectPartyViewModel
    private lateinit var binding: FragmentSelectPartyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = SelectPartyViewModel()
        binding = FragmentSelectPartyBinding.inflate(layoutInflater)
        viewModel.parties.observe(viewLifecycleOwner){
            binding.selectPartyRV.adapter = PartyAdapter(it)
        }
        return binding.root
    }
}

class SelectPartyViewModel: ViewModel(){
    var parties: LiveData<List<String>> = Transformations.map(MemberRepo.logData){
     it.map { it.party }.toSortedSet().toList()
    }
}