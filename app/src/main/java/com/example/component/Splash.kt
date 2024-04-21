package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {

    private lateinit var authentication: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash)

        //firebase authentication intialisation, the code logic was taken from here: [https://firebase.google.com/docs/auth/android/start]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
        authentication = FirebaseAuth.getInstance()

        //User not registered -> Registration View
        val registerBtn = findViewById<Button>(R.id.register)
        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        // User already registered -> Login View
        val loginBtn = findViewById<Button>(R.id.login)
        loginBtn.setOnClickListener {
            val loginView = Intent(this, Login::class.java)
            startActivity(loginView)
        }

        //If navigating via Continue as a guest, user navigates to the Guest View.
        val guestLink = findViewById<TextView>(R.id.guestView)
        guestLink.setOnClickListener {
            val guestView = Intent(this, GuestActivity::class.java)
            startActivity(guestView)
        }
    }

    //This class is the launcher activity of the app.
    //If the user has already logged in, he does not need to log on, so on start he is automatically logged in to home screen, unless he logs out.
    //Guidance for code taken from: [https://firebase.google.com/docs/auth/android/password-auth]
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