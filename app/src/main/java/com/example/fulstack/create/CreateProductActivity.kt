package com.example.fulstack.create

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.R
import com.example.fulstack.read.Product

class CreateProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val price = etPrice.text.toString()
            val phone = etPhone.text.toString()

            if (title.isNotEmpty() && price.isNotEmpty()) {
                val newProduct = Product(
                    id = System.currentTimeMillis().toInt(),
                    title = title,
                    price = price,
                    category = "Новое",
                    location = "Бишкек",
                    description = "Тел: $phone",
                    sellerName = "Я"
                )

                val resultIntent = Intent()
                resultIntent.putExtra("new_product", newProduct)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Заполните название и цену", Toast.LENGTH_SHORT).show()
            }
        }
    }
}