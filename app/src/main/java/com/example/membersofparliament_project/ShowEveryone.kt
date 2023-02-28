package com.example.membersofparliament_project

import ParliamentMembersData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.membersofparliament_project.databinding.FragmentShowEveryoneBinding

//This view lists every member in a recyclerview with the option to click on a name and then show more info about that member.
class ShowEveryone : Fragment() {
    lateinit var binding: FragmentShowEveryoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = EveryoneAdapter(ParliamentMembersData.members)
        binding = FragmentShowEveryoneBinding.inflate(layoutInflater)
        binding.everyoneRV.adapter = adapter

        // Inflate the layout for this fragment
        return binding.root
    }

}