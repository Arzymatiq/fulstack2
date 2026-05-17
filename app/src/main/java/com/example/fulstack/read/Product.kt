package com.example.fulstack.read

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    var title: String,      // var, чтобы можно было редактировать
    var price: String,      // var
    val category: String,
    val location: String,
    var description: String, // var
    val sellerName: String,
    var imageUri: String? = null // добавьте это поле, если используете фото
) : Parcelable
