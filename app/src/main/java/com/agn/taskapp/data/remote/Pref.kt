package com.agn.taskapp.data.remote

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref(private val context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(TASK_PREF_NAME, MODE_PRIVATE)

    fun isUserSeen(): Boolean {
        return pref.getBoolean(USER_SEEN_KEY, false)
    }

    fun saveUserSeen() {
        // apply для потвеждения действия
        pref.edit().putBoolean(USER_SEEN_KEY, true).apply()
    }

    companion object {
        const val TASK_PREF_NAME = "TaskPref"
        const val USER_SEEN_KEY = "user.seen"
    }

    // что бы получать и сохранить в самом приложении то есть это internal storage
    fun saveName(name: String) {
        pref.edit().putString("keyName", name).apply()
    }

    fun getName(): String {
        return pref.getString("keyName", "").toString()
    }

    fun saveImg(img: String) {
        pref.edit().putString("keyImg", img).apply()
    }

    fun getImg(): String {
        return pref.getString("keyImg", "").toString()
    }



}