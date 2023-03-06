package com.example.membersofparliament_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.membersofparliament_project.databinding.FragmentShowPartyBinding

class showParty : Fragment() {
    lateinit var viewModel: ShowPartyViewModel
    lateinit var binding: FragmentShowPartyBinding
    val args: showPartyArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ShowPartyViewModel(savedStateHandle = args.toSavedStateHandle())

        binding = FragmentShowPartyBinding.inflate(layoutInflater)
        viewModel.members.observe(viewLifecycleOwner){
            binding.partyRV.adapter = SinglePartyAdapter(it)
        }
        return binding.root
    }
}

class ShowPartyViewModel(savedStateHandle: SavedStateHandle): ViewModel(){
    val party: String? = savedStateHandle["party"]
    var members: LiveData<List<String>> = Transformations.map(ParliamentDB.getInstance().parliamentMemberDAO.getParty(party.toString())){
        it.map {"${it.firstname} ${it.lastname} Seat number: ${it.seatNumber}"}.toSortedSet().toList()
        }
}