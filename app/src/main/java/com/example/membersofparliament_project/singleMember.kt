package com.example.membersofparliament_project

import ParliamentMembersData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.membersofparliament_project.databinding.FragmentSingleMemberBinding

class singleMember : Fragment() {

    lateinit var binding: FragmentSingleMemberBinding
    val args: singleMemberArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val personNumber = args.number.toInt()
        val selectedPerson = ParliamentMembersData.members[personNumber]
        // Inflate the layout for this fragment
        binding = FragmentSingleMemberBinding.inflate(layoutInflater)
        binding.name.text = selectedPerson.first + " " + selectedPerson.last
        binding.party.text = selectedPerson.party
        binding.seatNumber.text = selectedPerson.seatNumber.toString()
        binding.constituency.text = selectedPerson.constituency
        binding.birthYear.text = selectedPerson.bornYear.toString()
        binding.imageView.setImageResource(R.drawable.perhaps)
        return binding.root
    }
}