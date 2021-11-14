package com.example.companythriftstore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.companythriftstore.MainActivity
import com.example.companythriftstore.R
import com.example.companythriftstore.helper.SheredPref
import com.example.companythriftstore.model.ResponseModel
import com.inyongtisto.tokoonline.app.ApiConfig
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edt_email
import kotlinx.android.synthetic.main.activity_register.edt_password
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var s: SheredPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        s= SheredPref(this)

        btn_register.setOnClickListener {
            register()
        }
    }
    fun register(){
        if(edt_nama.text.isEmpty()){
            edt_nama.error = "kolom nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        }else if(edt_email.text.isEmpty()){
            edt_email.error = "kolom email tidak boleh kosong"
            edt_email.requestFocus()
            return
        }else if(edt_phone.text.isEmpty()){
            edt_phone.error = "kolom Telephone tidak boleh kosong"
            edt_phone.requestFocus()
            return
        }else if(edt_password.text.isEmpty()){
            edt_password.error = "kolom password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }
        pb1.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(edt_nama.text.toString(), edt_email.text.toString(),edt_phone.text.toString(), edt_password.text.toString()).enqueue(object :Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                pb1.visibility = View.GONE
                val respon = response.body()!!
                if(respon.success==1){
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterActivity, "Selamat datang "+respon.user.name, Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this@RegisterActivity, "Error"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                pb1.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}