package com.example.fulstack.read

import java.io.Serializable

data class Product(
    val id: Int,
    var title: String,      // Используй var, чтобы можно было менять
    var price: String,
    var category: String,
    var location: String,
    var description: String,
    var sellerName: String,
    var imageUri: String? = null // Обязательно добавь это!
) : java.io.Serializable