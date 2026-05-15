package com.example.fulstack.read

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.R

class EditProductActivity : AppCompatActivity() {

    private lateinit var product: Product
    private var selectedImageUri: Uri? = null

    // Выбор картинки из галереи
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it
            findViewById<ImageView>(R.id.edit_image_preview).setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        product = intent.getSerializableExtra("product") as Product

        val etTitle = findViewById<EditText>(R.id.et_edit_title)
        val etPrice = findViewById<EditText>(R.id.et_edit_price)
        val etDesc = findViewById<EditText>(R.id.et_edit_description)
        val imgPreview = findViewById<ImageView>(R.id.edit_image_preview)

        // Заполняем текущими данными
        etTitle.setText(product.title)
        etPrice.setText(product.price)
        etDesc.setText(product.description)
        product.imageUri?.let { imgPreview.setImageURI(Uri.parse(it)) }

        findViewById<Button>(R.id.btn_change_photo).setOnClickListener {
            pickImage.launch("image/*")
        }

        findViewById<Button>(R.id.btn_save_changes).setOnClickListener {
            // Обновляем объект
            product.title = etTitle.text.toString()
            product.price = etPrice.text.toString()
            product.description = etDesc.text.toString()
            if (selectedImageUri != null) {
                product.imageUri = selectedImageUri.toString()
            }

            // Возвращаем результат назад
            val intent = Intent()
            intent.putExtra("updated_product", product)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}