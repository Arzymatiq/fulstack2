package com.example.fulstack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.read.ReadActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.item_product)
        startActivity(Intent(this, ReadActivity::class.java))
        finish()
    }
}