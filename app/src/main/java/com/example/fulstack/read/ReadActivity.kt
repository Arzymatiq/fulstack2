package com.example.fulstack.read

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulstack.LoginActivity
import com.example.fulstack.R

class ReadActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private lateinit var tvCount: TextView
    private val allProducts = getMockData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_products)
        adapter = ProductAdapter(allProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        tvCount = findViewById(R.id.tv_count)
        updateCount(allProducts.size)

        val etSearch = findViewById<EditText>(R.id.et_search)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterProducts(s.toString())
            }
        })

        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navFavorite = findViewById<LinearLayout>(R.id.navFavorite)
        val navAdd = findViewById<LinearLayout>(R.id.navAdd)
        val navProfile = findViewById<LinearLayout>(R.id.navProfile)

        navHome.setOnClickListener {
            Toast.makeText(this, "Главная", Toast.LENGTH_SHORT).show()
        }

        navFavorite.setOnClickListener {
            if (isLoggedIn()) {
                Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
            } else {
                openLogin()
            }
        }

        navAdd.setOnClickListener {
            if (isLoggedIn()) {
                Toast.makeText(this, "Добавить объявление", Toast.LENGTH_SHORT).show()
            } else {
                openLogin()
            }
        }

        navProfile.setOnClickListener {
            if (isLoggedIn()) {
                Toast.makeText(this, "Профиль", Toast.LENGTH_SHORT).show()
            } else {
                openLogin()
            }
        }
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun isLoggedIn(): Boolean {
        val prefs = getSharedPreferences("user", MODE_PRIVATE)
        return prefs.getBoolean("isLoggedIn", false)
    }

    private fun filterProducts(query: String) {
        val filtered = if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true) ||
                        it.location.contains(query, ignoreCase = true)
            }
        }

        adapter.updateList(filtered)
        updateCount(filtered.size)
    }

    private fun updateCount(count: Int) {
        tvCount.text = "Найдено объявлений: $count"
    }

    private fun getMockData(): List<Product> = listOf(
        Product(1, "iPhone 13 Pro 256GB", "45 000", "Телефоны", "Бишкек, Первомайский р-н",
            "Отличное состояние, полный комплект", "Азиз"),
        Product(2, "Nike Air Max 270", "3 500", "Обувь", "Бишкек, Свердловский р-н",
            "Носил 3 месяца, размер 42", "Максат"),
        Product(3, "MacBook Air M2", "85 000", "Ноутбуки", "Бишкек, Чуй пр-т",
            "2023 год, 8GB/256GB, идеал", "Нурлан"),
        Product(4, "Toyota Camry 2020", "2 800 000", "Автомобили", "Бишкек, Аламедин",
            "Пробег 45 000 км, один хозяин", "Бакыт"),
        Product(5, "Диван угловой", "15 000", "Мебель", "Бишкек, Арча-Бешик",
            "Светло-серый, 280x180 см, самовывоз", "Айгуль"),
        Product(6, "Samsung 65\" 4K", "55 000", "Электроника", "Бишкек, Аламедин р-н",
            "2022 год, Smart TV, в упаковке", "Тимур"),
        Product(7, "Велосипед горный", "8 000", "Спорт", "Бишкек, мкр Восток-5",
            "21 скорость, колёса 26, хорошее сост.", "Эрлан"),
        Product(8, "Квартира 2к, аренда", "20 000", "Недвижимость", "Бишкек, центр",
            "45 кв.м, 4 этаж, без посредников", "Салтанат")
    )
}