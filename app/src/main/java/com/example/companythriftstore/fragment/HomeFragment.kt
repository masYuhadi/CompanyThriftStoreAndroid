package com.example.companythriftstore.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.companythriftstore.MainActivity
import com.example.companythriftstore.R
import com.example.companythriftstore.adapter.AdapterProduk
import com.example.companythriftstore.model.Produk
import com.example.companythriftstore.model.ResponseModel
import com.inyongtisto.tokoonline.app.ApiConfig
import com.inyongtisto.tutorial.adapter.AdapterSlider
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var vpslider: ViewPager
    lateinit var rvproduk: RecyclerView
    lateinit var rvproduk2:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        getProduk()

        return view
    }

    fun displayProduk(){

        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.star_wars)
        arrSlider.add(R.drawable.star_wars2)
        arrSlider.add(R.drawable.ironman)

        val adapterSlider= AdapterSlider(arrSlider, activity)
        vpslider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        rvproduk.adapter = AdapterProduk(listProduk)
        rvproduk.layoutManager = layoutManager

        rvproduk2.adapter = AdapterProduk(listProduk)
        rvproduk2.layoutManager = layoutManager2
    }

    private var listProduk: ArrayList<Produk> = ArrayList()
    fun getProduk(){
        ApiConfig.instanceRetrofit.getproduk().enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val res = response.body()!!
                if(res.success == 1){
                    listProduk = res.produks
                    displayProduk()
                }
            }
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
            }
        })
    }

    fun init(view: View){
        vpslider = view.findViewById(R.id.vp_geser)
        rvproduk = view.findViewById(R.id.rv_produk)
        rvproduk2 = view.findViewById(R.id.rv_produk2)
    }


//
//    val arrProduk: java.util.ArrayList<Produk>get(){
//        val arr = java.util.ArrayList<Produk>()
//        val p1 = Produk()
//        p1.nama = "baju keren1"
//        p1.harga = "Rp. 150.000"
//        p1.gambar= R.drawable.k1
//
//        val p2 = Produk()
//        p2.nama = "baju keren2"
//        p2.harga = "Rp. 180.000"
//        p2.gambar= R.drawable.k2
//
//        val p3 = Produk()
//        p3.nama = "baju keren3"
//        p3.harga = "Rp. 160.000"
//        p3.gambar= R.drawable.k3
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//        return arr
//    }
//    val arrProduk2: java.util.ArrayList<Produk>get(){
//        val arr = java.util.ArrayList<Produk>()
//        val p4 = Produk()
//        p4.nama = "baju keren4"
//        p4.harga = "Rp. 150.000"
//        p4.gambar= R.drawable.k4
//
//        val p5 = Produk()
//        p5.nama = "baju keren5"
//        p5.harga = "Rp. 180.000"
//        p5.gambar= R.drawable.k5
//
//        val p6 = Produk()
//        p6.nama = "baju keren6"
//        p6.harga = "Rp. 160.000"
//        p6.gambar= R.drawable.k6
//
//        arr.add(p4)
//        arr.add(p5)
//        arr.add(p6)
//        return arr
//    }


}