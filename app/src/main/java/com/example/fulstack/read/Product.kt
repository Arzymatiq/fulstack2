package com.example.fulstack.read

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    var title: String,
    var price: String,
    val category: String,
    val location: String,
    var description: String,
    val sellerName: String,
    var imageUri: String? = null
) : Parcelable


data class Product2(
    val id: Int,
    var title: String,
    var price: String,
    var category: String,
    var location: String,
    var description: String,
    var sellerName: String,
    var imageUri: String? = null
) : java.io.Serializable

