package com.example.membersofparliament_project

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class ParliamentMember(
    @PrimaryKey
    val hetekaId: Int,
    val seatNumber: Int = 0,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean = true,
    val pictureUrl: String = ""
)

@Dao
interface ParliamentMemberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: ParliamentMember)
    @Query("select * from ParliamentMember")
    fun getAll(): LiveData<List<ParliamentMember>>
    @Query("SELECT * FROM ParliamentMember WHERE party = :party")
    fun getParty(party: String): LiveData<List<ParliamentMember>>
    @Query("SELECT * FROM ParliamentMember WHERE hetekaId = :id")
    fun getMember(id: Int?): LiveData<List<ParliamentMember>>
}