package com.agn.taskapp

import android.app.Application
import androidx.room.Room
import com.agn.taskapp.data.local.db.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        // allowMainThreadQueries() - для работы в главном в потоке
    }

    companion object {
        lateinit var db: AppDatabase
    }
}