package com.example.fulstack

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tabLogin = findViewById<TextView>(R.id.tabLogin)
        val tabRegister = findViewById<TextView>(R.id.tabRegister)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        tabLogin.setOnClickListener {

            isLoginMode = true

            tabLogin.setTextColor(getColor(R.color.orange))
            tabRegister.setTextColor(getColor(android.R.color.darker_gray))

            btnLogin.text = "Войти"
        }

        tabRegister.setOnClickListener {

            isLoginMode = false

            tabRegister.setTextColor(getColor(R.color.orange))
            tabLogin.setTextColor(getColor(android.R.color.darker_gray))

            btnLogin.text = "Зарегистрироваться"
        }

        btnLogin.setOnClickListener {

            if (username.text.toString().isNotEmpty() &&
                password.text.toString().isNotEmpty()
            ) {

                val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)

                prefs.edit()
                    .putBoolean("isLoggedIn", true)
                    .putString("username", username.text.toString())
                    .apply()

                Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show()

                finish()

            } else {

                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}