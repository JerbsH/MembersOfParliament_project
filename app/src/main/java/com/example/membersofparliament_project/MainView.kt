package com.example.membersofparliament_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.membersofparliament_project.databinding.FragmentMainViewBinding
import kotlinx.coroutines.launch

//Shows two buttons with options what to view.
//Show everyone button shows a list of all members and show party shows the list of parties.
class MainView : Fragment() {
    lateinit var binding: FragmentMainViewBinding
    lateinit var viewModel: MainViewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = MainViewViewModel()
        binding = FragmentMainViewBinding.inflate(layoutInflater)
        binding.btnEveryone.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_showEveryone)
        }
        binding.btnParty.setOnClickListener {
            findNavController().navigate(R.id.action_mainView_to_selectParty)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        viewModel.readMembers()
        viewModel.member.observe(viewLifecycleOwner){
            println("member changed")
        }
    }
}

class MainViewViewModel: ViewModel(){
    var member: MutableLiveData<List<ParliamentMember>> = MutableLiveData()

    fun readMembers(){
        viewModelScope.launch {
            try {
                val dao = ParliamentDB.getInstance().parliamentMemberDAO
                member.value = ParliamentAPI.retrofitService.getPlayerList()
                println("Read members from parliament with great success.")
                member.value?.forEach{
                    dao.insert(it)
                }
                println("Written to database")
            } catch (e: Exception){
                println("No luck in reading members from parliament: $e")
            }
        }
    }
}