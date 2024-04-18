package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

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

        authentication = FirebaseAuth.getInstance()

        val backBtn = findViewById<Button>(R.id.backLogin)
        backBtn.setOnClickListener {
            val backLog = Intent(this, Login::class.java)
            startActivity(backLog)
        }

        val resetEmailLayout = findViewById<TextInputLayout>(R.id.resetEmail)
        val resetEmail = findViewById<TextInputEditText>(R.id.email)

        val resetPassword = findViewById<Button>(R.id.resetPass)

        resetPassword.setOnClickListener {
            val email = resetEmail.text.toString().trim()
            if (email.isEmpty()) {
                resetEmailLayout.error = "Error: Email fields are required"
            } else {
                resetEmailLayout.error = null
                authentication.sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        successDialog()
                    }
            }
        }
    }

    private fun successDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Password Reset Email Sent!!")
            .setMessage("Please check your inbox for full detailed instructions on resetting the password. Once, you have reset your password, please navigate yourself to the login page. The password reset email is only valid for 1 hour.")
            .setNegativeButton("Ok", null)

            .show()
    }
}