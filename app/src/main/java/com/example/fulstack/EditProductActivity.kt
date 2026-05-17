package com.example.fulstack.read

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // Используем getParcelableExtra, так как ваш класс Product помечен @Parcelize
        product = intent.getParcelableExtra<Product>("product")!!

        val etTitle = findViewById<EditText>(R.id.et_edit_title)
        val etPrice = findViewById<EditText>(R.id.et_edit_price)
        val etDesc = findViewById<EditText>(R.id.et_edit_description)
        val imgPreview = findViewById<ImageView>(R.id.edit_image_preview)

        // Заполняем поля текущими данными
        etTitle.setText(product.title)
        etPrice.setText(product.price)
        etDesc.setText(product.description)
        product.imageUri?.let { imgPreview.setImageURI(Uri.parse(it)) }

        // Кнопка изменения фото
        findViewById<Button>(R.id.btn_change_photo).setOnClickListener {
            pickImage.launch("image/*")
        }

        // Кнопка сохранения изменений
        findViewById<Button>(R.id.btn_save_changes).setOnClickListener {
            // Обновляем данные в объекте
            product.title = etTitle.text.toString()
            product.price = etPrice.text.toString()
            product.description = etDesc.text.toString()
            if (selectedImageUri != null) {
                product.imageUri = selectedImageUri.toString()
            }

            // СОХРАНЯЕМ В РЕПОЗИТОРИЙ (Самое важное)
            ProductRepository.update(product)

            finish() // Просто закрываем экран, остальные подхватят данные из репозитория
        }

        // КНОПКА УДАЛЕНИЯ ТОВАРА
        findViewById<Button>(R.id.btnDeleteProduct).setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun showDeleteDialog() {
        AlertDialog.Builder(this)
            .setTitle("Удаление товара")
            .setMessage("Вы уверены, что хотите удалить этот товар?")
            .setPositiveButton("Удалить") { _, _ ->
                // УДАЛЯЕМ ИЗ РЕПОЗИТОРИЯ
                ProductRepository.delete(product.id)
                finish()
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}