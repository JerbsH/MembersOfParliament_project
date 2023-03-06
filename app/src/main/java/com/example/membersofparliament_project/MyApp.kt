package com.example.membersofparliament_project

import android.app.Application
import android.content.Context

class MyApp: Application() {
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * Class to use .MyApp in code
    */
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate(){
            super.onCreate()
            appContext = applicationContext
        }
    }
