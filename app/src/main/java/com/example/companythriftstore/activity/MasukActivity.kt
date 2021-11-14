package com.example.companythriftstore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.companythriftstore.R
import com.example.companythriftstore.helper.SheredPref
import kotlinx.android.synthetic.main.activity_masuk.*

class MasukActivity : AppCompatActivity() {
    lateinit var s:SheredPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        s= SheredPref(this)


        mainButton()
    }
    fun mainButton(){
        btn_Proseslogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btn_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}