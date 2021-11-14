package com.example.companythriftstore.model

import kotlin.collections.ArrayList

class ResponseModel {
    var success = 0
    lateinit var message :String
    var user = UserM()
    var produks: ArrayList<Produk> = ArrayList()
}