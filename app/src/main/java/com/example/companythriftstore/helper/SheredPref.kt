package com.example.companythriftstore.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.companythriftstore.model.UserM
import com.google.gson.Gson

class SheredPref (activity: Activity) {
    val login = "login"
    val nama = "nama"
    val email = "email"
    val phone = "phone"

    val user = "user"


    val mypref = "MAIN_PRF"
    val sp:SharedPreferences

    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }
    fun setStatusLogin(status:Boolean){
        sp.edit().putBoolean(login, status).apply()
    }
    fun getStatusLogin():Boolean{
        return sp.getBoolean(login, false)
    }
    fun setUser(value: UserM){
        val data:String = Gson().toJson(value, UserM::class.java)
        sp.edit().putString(user, data).apply()
    }
    fun getUser(): UserM? {
        val data:String =  sp.getString(user, null)?: return null
        return Gson().fromJson(data, UserM::class.java)
    }
    fun setString(key:String, value:String){
        sp.edit().putString(key, value).apply()
    }
    fun getstring(key:String):String{
        return sp.getString(key, "")!!
    }
}