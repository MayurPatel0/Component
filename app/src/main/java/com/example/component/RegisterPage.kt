package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterPage : AppCompatActivity() {

    private lateinit var authentication: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)

        authentication = FirebaseAuth.getInstance()

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val backToSplash = Intent(this, Splash::class.java)
            startActivity(backToSplash)
        }

        val enterEmailAddress = findViewById<TextInputEditText>(R.id.email)
        val enterPassword = findViewById<TextInputEditText>(R.id.password)
        val enterConfirmPassword = findViewById<TextInputEditText>(R.id.confirmPassword)

        val registerButton = findViewById<Button>(R.id.completeRegistration)
        registerButton.setOnClickListener {
            val emailAddress = enterEmailAddress.text.toString()
            val password = enterPassword.text.toString()

            authentication.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this) { task  ->
                    if (task.isSuccessful) {
                        val registerComplete = Intent(this, MainActivity::class.java)
                        startActivity(registerComplete)
                        //finish()
                    }
                    else {
                        //inline error messages!
                    }
                }
        }


        val loginButton = findViewById<Button>(R.id.loginView)
        loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}