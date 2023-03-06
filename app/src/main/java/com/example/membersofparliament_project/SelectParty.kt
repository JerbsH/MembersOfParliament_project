package com.example.membersofparliament_project

import ParliamentMembersData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.membersofparliament_project.databinding.FragmentSelectPartyBinding

class SelectParty : Fragment() {
    lateinit var viewModel: SelectPartyViewModel
    lateinit var binding: FragmentSelectPartyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = SelectPartyViewModel()
        binding = FragmentSelectPartyBinding.inflate(layoutInflater)
        viewModel.parties.observe(viewLifecycleOwner){
            binding.selectPartyRV.adapter = PartyAdapter(it)
        }
        return binding.root
    }
}

class SelectPartyViewModel: ViewModel(){
    var parties: LiveData<List<String>> = Transformations.map(ParliamentDB.getInstance().parliamentMemberDAO.getAll()){
     it.map { it.party }.toSortedSet().toList()
    }
}