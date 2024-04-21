package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class ForgotPassword : AppCompatActivity() {

    private lateinit var authentication: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //firebase authentication intialisation, the code logic was taken from here: [https://firebase.google.com/docs/auth/android/start]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
        authentication = FirebaseAuth.getInstance()

        val backBtn = findViewById<Button>(R.id.backLogin)
        backBtn.setOnClickListener {
            val backLog = Intent(this, Login::class.java)
            startActivity(backLog)
        }

        //Referencing the input fields edit and the layout by their ID's from XML.
        val resetEmailLayout = findViewById<TextInputLayout>(R.id.resetEmail)
        val resetEmail = findViewById<TextInputEditText>(R.id.email)

        resetEmail.doOnTextChanged { text, start, before, count ->
            val email = text.toString()
            if (isEmailValid(email))  {
                resetEmailLayout.error = null
            }
            else {
                resetEmailLayout.error = "Error: Please enter a valid email address."

            }
        }

        val resetPassword = findViewById<Button>(R.id.resetPass)

        resetPassword.setOnClickListener {
            val email = resetEmail.text.toString().trim()
            if (email.isEmpty()) {
                resetEmailLayout.error = "Error: Email fields are required"
            } else {
                resetEmailLayout.error = null
                authentication.sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        //Password Reset Email is sent!
                        successDialog()
                    }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val regex = Pattern.compile(
            "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"
        )
        return regex.matcher(email).matches()
    }

    private fun successDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Password Reset Email Sent!!")
            .setMessage("Please check your email inbox for full detailed instructions on resetting the password. Once, you have reset your password, please navigate yourself to the login page. The password reset email is only valid for 1 hour.")
            .setNegativeButton("Ok", null)

            .show()
    }
}