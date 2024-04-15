package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var authentication: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)

        authentication = FirebaseAuth.getInstance()

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val backToSplash = Intent(this, Splash::class.java)
            startActivity(backToSplash)
        }

        val enterEmailAddress = findViewById<TextInputEditText>(R.id.email)
        val enterPassword = findViewById<TextInputEditText>(R.id.password)

        val goToRegisterButton = findViewById<Button>(R.id.registerView)

        goToRegisterButton.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        val completeLoginButton = findViewById<Button>(R.id.completeLogin)

        completeLoginButton.setOnClickListener {
            val emailAddress = enterEmailAddress.text.toString()
            val password = enterPassword.text.toString()

            authentication.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        val loginComplete = Intent(this, MainActivity::class.java)
                        startActivity(loginComplete)
                        //finish()
                    }
                    else {

                    }
                }
        }

    }
}