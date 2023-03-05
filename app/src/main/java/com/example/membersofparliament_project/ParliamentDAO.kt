package com.example.membersofparliament_project

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class ParliamentMember(
    @PrimaryKey
    val hetekaId: Int,
    val seatNumber: Int = 0,
    val lastName: String,
    val firstName: String,
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
}