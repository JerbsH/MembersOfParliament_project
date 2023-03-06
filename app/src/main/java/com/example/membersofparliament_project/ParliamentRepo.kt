package com.example.membersofparliament_project

import androidx.lifecycle.LiveData

object MemberRepo {
    private val dao = ParliamentDB.getInstance().parliamentMemberDAO
    val logData: LiveData<List<ParliamentMember>> = dao.getAll()

    suspend fun insertMembers(members: ParliamentMember){
        dao.insert(members)
    }

    fun getPartyMembers(party: String): LiveData<List<ParliamentMember>>{
        return dao.getParty(party)
    }
    fun getSingleMember(id: Int?): LiveData<List<ParliamentMember>>{
        return dao.getMember(id)
    }
}