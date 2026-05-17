package com.example.fulstack.read

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.R

class DetailActivity : AppCompatActivity() {

    // 1. Объявляем launcher здесь (вне onCreate)
    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updated = result.data?.getParcelableExtra<Product>("updated_product")
            updated?.let {
                // Обновляем текст и фото на экране после редактирования
                findViewById<TextView>(R.id.detail_title).text = it.title
                findViewById<TextView>(R.id.detail_price).text = "${it.price} сом"
                findViewById<TextView>(R.id.detail_description).text = it.description
                it.imageUri?.let { uri ->
                    findViewById<ImageView>(R.id.detail_image).setImageURI(Uri.parse(uri))
                }
                Toast.makeText(this, "Данные обновлены!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Получаем данные из интента
        val title       = intent.getStringExtra("title") ?: ""
        val price       = intent.getStringExtra("price") ?: ""
        val category    = intent.getStringExtra("category") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val location    = intent.getStringExtra("location") ?: ""
        val seller      = intent.getStringExtra("sellerName") ?: ""

        // Привязываем к View
        findViewById<TextView>(R.id.detail_title).text       = title
        findViewById<TextView>(R.id.detail_price).text       = "$price сом"
        findViewById<TextView>(R.id.detail_category).text    = category
        findViewById<TextView>(R.id.detail_description).text = description
        findViewById<TextView>(R.id.detail_location).text    = "📍 $location"
        findViewById<TextView>(R.id.detail_seller).text      = "👤 $seller"

        // 2. Создаем объект текущего продукта для передачи на экран редактирования
        val currentProduct = Product(
            id = 1,
            title = title,
            price = price,
            category = category,
            location = location,
            description = description,
            sellerName = seller
        )

        // 3. Обработка кнопки "Редактировать"
        findViewById<Button>(R.id.btn_edit).setOnClickListener {
            val intent = Intent(this, EditProductActivity::class.java)
            intent.putExtra("product", currentProduct)
            editLauncher.launch(intent)
        }

        findViewById<TextView>(R.id.btn_back).setOnClickListener {
            finish()
        }
    }
}