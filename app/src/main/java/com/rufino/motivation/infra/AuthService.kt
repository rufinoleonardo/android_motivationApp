package com.rufino.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class AuthService (context: Context) {
    private val storage: SharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun setOnStorage(key: String, name: String){
        storage.edit().putString(key, name).apply()
    }

    fun getOnStorage(key:String): String = storage.getString(key, "") ?: ""
}