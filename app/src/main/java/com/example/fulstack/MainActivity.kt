package com.example.fulstack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fulstack.read.ReadActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Сразу переходим на экран списка объявлений
        startActivity(Intent(this, ReadActivity::class.java))
        finish() // чтобы MainActivity не оставалась в стеке
    }
}