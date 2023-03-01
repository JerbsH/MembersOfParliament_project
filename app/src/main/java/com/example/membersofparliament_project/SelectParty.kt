package com.example.membersofparliament_project

import ParliamentMembersData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.membersofparliament_project.databinding.FragmentSelectPartyBinding

class SelectParty : Fragment() {

    lateinit var binding: FragmentSelectPartyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val partyList = getParties()
        val adapter = PartyAdapter(partyList)
        binding = FragmentSelectPartyBinding.inflate(layoutInflater)
        binding.selectPartyRV.adapter = adapter
        return binding.root
    }

    //Creates a list of all the parties that are found in the member data
    fun getParties(): List<String> {
        val partyList = mutableSetOf<String>()
        var i = 0
        do {
            partyList.add(ParliamentMembersData.members[i].party)
            i++
        } while (i<ParliamentMembersData.members.size)
        Log.d("bug", partyList.toString())
        return partyList.toList()
    }
}