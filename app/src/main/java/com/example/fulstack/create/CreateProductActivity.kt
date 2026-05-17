package com.example.fulstack.create

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.R
import com.example.fulstack.read.Product
import com.example.fulstack.read.ProductRepository // Импортируем репозиторий

class CreateProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val price = etPrice.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (title.isNotEmpty() && price.isNotEmpty()) {
                val newProduct = Product(
                    id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(), // Безопасный уникальный ID
                    title = title,
                    price = price,
                    category = "Новое",
                    location = "Бишкек",
                    description = "Тел: $phone",
                    sellerName = "Я"
                )

                ProductRepository.add(newProduct)

                // 3. Сообщаем пользователю и закрываем экран
                Toast.makeText(this, "Товар добавлен!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Заполните название и цену", Toast.LENGTH_SHORT).show()
            }
        }
    }
}