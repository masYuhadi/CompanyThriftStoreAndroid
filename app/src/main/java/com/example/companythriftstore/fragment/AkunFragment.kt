package com.example.companythriftstore.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.companythriftstore.MainActivity
import com.example.companythriftstore.R
import com.example.companythriftstore.activity.LoginActivity
import com.example.companythriftstore.helper.SheredPref
import kotlinx.android.synthetic.main.fragment_akun.*


/**
 * A simple [Fragment] subclass.
 */
class AkunFragment : Fragment() {
    lateinit var s:SheredPref
    lateinit var btnLogout:TextView
    lateinit var tvnama:TextView
    lateinit var tvphone:TextView
    lateinit var tvemail:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_akun, container, false)
        init(view)

        s = activity?.let { SheredPref(it) }!!

        btnLogout.setOnClickListener {
            s.setStatusLogin(false)
        }
        setData()
        return view
    }
    fun setData(){

        if(s.getUser()==null){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }

        val user = s.getUser()!!


        tvnama.text =user.name
        tvemail.text =user.email
        tvphone.text =user.phone
    }

    private fun init(view: View) {
        btnLogout=view.findViewById(R.id.btn_logout)
        tvnama=view.findViewById(R.id.tv_nama)
        tvemail=view.findViewById(R.id.tv_email)
        tvphone=view.findViewById(R.id.tv_phone)
    }


}