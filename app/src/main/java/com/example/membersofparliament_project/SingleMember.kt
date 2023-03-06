package com.example.membersofparliament_project

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.membersofparliament_project.databinding.FragmentSingleMemberBinding

class SingleMember : Fragment() {
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * Fragment for showing single member view.
    * Lists name, party, seat number and id.
    */
    lateinit var viewModel: SingleMemberViewModel
    lateinit var binding: FragmentSingleMemberBinding
    val args: SingleMemberArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("test", "args value alussa: $args")
        //Gets the person ID from the arguments
        val correctId = args.number.split(":").last()
        Log.d("test", "id value: $correctId")
        viewModel = SingleMemberViewModel(correctId.toInt())
        Log.d("test", viewModel.members.toString())
        binding = FragmentSingleMemberBinding.inflate(layoutInflater)
        viewModel.members.observe(viewLifecycleOwner){
            //Sets information to a textView and removes [ from the beginning and ] from the end
            binding.textView3.text = it.toString().removePrefix("[").removeSuffix("]")
        }
        return binding.root
    }

}

class SingleMemberViewModel(val id: Int?): ViewModel(){
    var members: LiveData<List<String>> = Transformations.map(MemberRepo.getSingleMember(id)){
        it.map { "ID: ${it.hetekaId} \nName: ${it.firstname} ${it.lastname} \nSeat number: ${it.seatNumber} \nParty: ${it.party}"}
    }
}