package com.example.membersofparliament_project

import MemberOfParliament
import ParliamentMembersData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.membersofparliament_project.databinding.FragmentShowPartyBinding

class showParty : Fragment() {

    lateinit var binding: FragmentShowPartyBinding
    val args: showPartyArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Log.d("bug", args.toString())
        val members = getMembers()
        val adapter = SinglePartyAdapter(members)
        binding = FragmentShowPartyBinding.inflate(layoutInflater)
        binding.partyRV.adapter = adapter

        return binding.root
    }
    private fun getMembers(): List<MemberOfParliament> {
        val memberList = mutableListOf<MemberOfParliament>()
        var i = 0
        do {
            if (ParliamentMembersData.members[i].party == args.party){
                memberList.add(ParliamentMembersData.members[i])
            }
            i++
        } while (i < ParliamentMembersData.members.size)
        return memberList
    }

}