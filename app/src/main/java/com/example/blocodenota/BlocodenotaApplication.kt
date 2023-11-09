package com.example.blocodenota

import android.app.Application
import androidx.room.Room
import com.example.blocodenota.data.local.AppDataBase

class BlocodenotaApplication:Application() {

   private lateinit var dataBase: AppDataBase

    override fun onCreate(){
        super.onCreate()

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "blocodenota-database"
        ).build()

    }

    fun getAppDataBase(): AppDataBase {
        return dataBase
    }
}