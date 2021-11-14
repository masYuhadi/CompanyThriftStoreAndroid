package com.example.companythriftstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.companythriftstore.activity.LoginActivity
import com.example.companythriftstore.activity.MasukActivity
import com.example.companythriftstore.fragment.AkunFragment
import com.example.companythriftstore.fragment.HomeFragment
import com.example.companythriftstore.fragment.KeranjangFragment
import com.example.companythriftstore.helper.SheredPref
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val fragmenthome: Fragment = HomeFragment()
    val fragmentakun: Fragment = AkunFragment()
    val fragmentKeranjang: Fragment = KeranjangFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragmenthome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private var statusLogin = false

    private lateinit var s:SheredPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        s = SheredPref(this)

        setUpBottomNav()
    }
    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container,fragmenthome).show(fragmenthome).commit()
        fm.beginTransaction().add(R.id.container,fragmentakun).hide(fragmentakun).commit()
        fm.beginTransaction().add(R.id.container,fragmentKeranjang).hide(fragmentKeranjang).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0 )
        menuItem.isChecked = true

        bottomNavigationView.setOnItemSelectedListener{ item ->

            when(item.itemId) {
                R.id.navigation_home -> {
                    callfragment(0,fragmenthome)
                }
                R.id.navigation_keranjang -> {
                    callfragment(1,fragmentKeranjang)
                }
                R.id.navigation_akun -> {
                    if (s.getStatusLogin()) {
                        callfragment(2,fragmentakun)
                    }else {
                        startActivity(Intent(this, MasukActivity::class.java))
                    }

                }
            }

            false
        }
    }
    fun callfragment(int: Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}