package com.example.fulstack.read

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.R

class DetailActivity : AppCompatActivity() {

    private lateinit var currentProduct: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val product = intent.getParcelableExtra<Product>("product")
        if (product == null) {
            finish()
            return
        }
        currentProduct = product


        displayProduct()


        findViewById<Button>(R.id.btn_edit).setOnClickListener {
            val intent = Intent(this, EditProductActivity::class.java)
            intent.putExtra("product", currentProduct)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.btn_back).setOnClickListener { finish() }
    }


    override fun onResume() {
        super.onResume()

        val updatedProduct = ProductRepository.getAll().find { it.id == currentProduct.id }
        if (updatedProduct != null) {
            currentProduct = updatedProduct
            displayProduct()
        } else {

            finish()
        }
    }

    private fun displayProduct() {
        findViewById<TextView>(R.id.detail_title).text = currentProduct.title
        findViewById<TextView>(R.id.detail_price).text = "${currentProduct.price} сом"
        findViewById<TextView>(R.id.detail_description).text = currentProduct.description
        findViewById<TextView>(R.id.detail_category).text = currentProduct.category
        findViewById<TextView>(R.id.detail_location).text = "📍 ${currentProduct.location}"
        findViewById<TextView>(R.id.detail_seller).text = "👤 ${currentProduct.sellerName}"

        currentProduct.imageUri?.let {
            findViewById<ImageView>(R.id.detail_image).setImageURI(Uri.parse(it))
        }
    }
}