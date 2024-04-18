package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {

    private lateinit var authentication: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash)

        authentication = FirebaseAuth.getInstance()

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

    override fun onStart() {
        super.onStart()
        val alreadyLoggedUser = authentication.currentUser

        if (alreadyLoggedUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}