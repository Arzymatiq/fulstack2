package com.example.fulstack.read

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

        val productFromIntent = intent.getParcelableExtra<Product>("product")
        if (productFromIntent == null) {
            finish()
            return
        }
        currentProduct = productFromIntent

        findViewById<Button>(R.id.btn_edit).setOnClickListener {
            val intent = Intent(this, EditProductActivity::class.java)
            intent.putExtra("product", currentProduct)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.btn_back).setOnClickListener {
            finish()
        }

        updateUI(currentProduct)
    }

    override fun onResume() {
        super.onResume()
        // Берем актуальную версию этого товара из нашего репозитория по ID
        val updatedProduct = ProductRepository.getAll().find { it.id == currentProduct.id }

        updatedProduct?.let {
            currentProduct = it
            updateUI(it)
        }
    }

    private fun updateUI(product: Product) {
        findViewById<TextView>(R.id.detail_title).text = product.title
        findViewById<TextView>(R.id.detail_price).text = "${product.price} сом"
        findViewById<TextView>(R.id.detail_category).text = product.category
        findViewById<TextView>(R.id.detail_description).text = product.description
        findViewById<TextView>(R.id.detail_location).text = "📍 ${product.location}"
        findViewById<TextView>(R.id.detail_seller).text = "👤 ${product.sellerName}"

        val imageView = findViewById<ImageView>(R.id.detail_image)
        if (product.imageUri != null) {
            imageView.setImageURI(Uri.parse(product.imageUri))
        }
    }
}