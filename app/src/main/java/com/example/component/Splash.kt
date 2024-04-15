package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash)

        val registerBtn = findViewById<Button>(R.id.register)
        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        val loginBtn = findViewById<Button>(R.id.login)
        loginBtn.setOnClickListener {
            val loginView = Intent(this, Login::class.java)
            startActivity(loginView)
        }
        
        val guestLink = findViewById<TextView>(R.id.guestView)
        guestLink.setOnClickListener {
            val guestView = Intent(this, GuestActivity::class.java)
            startActivity(guestView)
        }
    }
}