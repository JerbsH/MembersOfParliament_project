package com.example.membersofparliament_project

import android.os.Bundle
import android.util.Log
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
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * First fragment with two buttons.
    * First button lists all members.
    * Second button lists all parties.
    */
    private lateinit var binding: FragmentMainViewBinding
    private lateinit var viewModel: MainViewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("test", "Oncreateview called")
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
        Log.d("test", "onviewcreated called")
        viewModel.readMembers()
        viewModel.member.observe(viewLifecycleOwner){
            println("member changed")
        }
    }
}

class MainViewViewModel: ViewModel(){
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * MainView ViewModel gets the members to database from the source
    */
    var member: MutableLiveData<List<ParliamentMember>> = MutableLiveData()
    fun readMembers(){
        Log.d("test", "readMembers called")
        viewModelScope.launch {
            try {
                val dao = ParliamentDB.getInstance().parliamentMemberDAO
                Log.d("test", "in try")
                member.value = ParliamentAPI.retrofitService.getMemberlist()
                println("Read members from parliament with great success.")
                member.value?.forEach{
                    dao.insert(it)
                }
                println("Written to database")
            } catch (e: Exception){
                Log.d("test", "in catch")
                println("No luck in reading members from parliament: $e")
            }
        }

    }
}