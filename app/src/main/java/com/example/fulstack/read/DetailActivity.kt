package com.example.fulstack.read

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title       = intent.getStringExtra("title") ?: ""
        val price       = intent.getStringExtra("price") ?: ""
        val category    = intent.getStringExtra("category") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val location    = intent.getStringExtra("location") ?: ""
        val seller      = intent.getStringExtra("sellerName") ?: ""

        findViewById<TextView>(R.id.detail_title).text       = title
        findViewById<TextView>(R.id.detail_price).text       = "$price сом"
        findViewById<TextView>(R.id.detail_category).text    = category
        findViewById<TextView>(R.id.detail_description).text = description
        findViewById<TextView>(R.id.detail_location).text    = "📍 $location"
        findViewById<TextView>(R.id.detail_seller).text      = "👤 $seller"

        findViewById<TextView>(R.id.btn_back).setOnClickListener {
            finish()
        }
    }
}