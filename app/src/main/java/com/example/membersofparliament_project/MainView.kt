package com.example.membersofparliament_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.membersofparliament_project.databinding.FragmentMainViewBinding

//Shows two buttons with options what to view.
//Show everyone button shows a list of all members and show party shows the list of parties.
class MainView : Fragment() {
    lateinit var _binding: FragmentMainViewBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainViewBinding.inflate(layoutInflater)
        binding.btnEveryone.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_showEveryone)
        }
        binding.btnParty.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_selectParty)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}