package com.example.fulstack.read

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.R

class EditProductActivity : AppCompatActivity() {

    private lateinit var product: Product
    private var selectedImageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it
            findViewById<ImageView>(R.id.edit_image_preview).setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        product = intent.getParcelableExtra<Product>("product")!!

        val etTitle = findViewById<EditText>(R.id.et_edit_title)
        val etPrice = findViewById<EditText>(R.id.et_edit_price)
        val etDesc = findViewById<EditText>(R.id.et_edit_description)
        val imgPreview = findViewById<ImageView>(R.id.edit_image_preview)
        etTitle.setText(product.title)
        etPrice.setText(product.price)
        etDesc.setText(product.description)
        product.imageUri?.let { imgPreview.setImageURI(Uri.parse(it)) }

        findViewById<Button>(R.id.btn_change_photo).setOnClickListener {
            pickImage.launch("image/*")
        }

        findViewById<Button>(R.id.btn_save_changes).setOnClickListener {

            product.title = etTitle.text.toString()
            product.price = etPrice.text.toString()
            product.description = etDesc.text.toString()
            if (selectedImageUri != null) {
                product.imageUri = selectedImageUri.toString()
            }

            ProductRepository.update(product)

            finish()
        }
    }
}