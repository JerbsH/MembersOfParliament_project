package com.example.membersofparliament_project

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ParliamentMember::class], version = 6, exportSchema = false)
abstract class ParliamentDB: RoomDatabase() {
    abstract val parliamentMemberDAO: ParliamentMemberDAO
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDB? = null
        fun getInstance(): ParliamentDB {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(MyApp.appContext, ParliamentDB::class.java, "member_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}