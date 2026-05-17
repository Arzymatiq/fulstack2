package com.example.fulstack.read

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fulstack.R
import com.example.fulstack.create.CreateProductActivity

class ReadActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private lateinit var tvCount: TextView

    private var allProducts = ProductRepository.getAll().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        val btnPlus = findViewById<Button>(R.id.btnCreateProduct)
        btnPlus.setOnClickListener {
            val intent = Intent(this, CreateProductActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_products)
        adapter = ProductAdapter(allProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        tvCount = findViewById(R.id.tv_count)
        updateCount(allProducts.size)

        val etSearch = findViewById<EditText>(R.id.et_search)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { filterProducts(s.toString()) }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {

        allProducts = ProductRepository.getAll().toMutableList()
        adapter.updateList(allProducts)
        updateCount(allProducts.size)
    }

    private fun filterProducts(query: String) {
        val filtered = if (query.isEmpty()) {
            ProductRepository.getAll()
        } else {
            ProductRepository.getAll().filter { it.title.contains(query, ignoreCase = true) }
        }
        adapter.updateList(filtered.toMutableList())
        updateCount(filtered.size)
    }

    private fun updateCount(count: Int) {
        tvCount.text = "Найдено объявлений: $count"
    }
}