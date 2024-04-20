package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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

        val emailLayout = findViewById<TextInputLayout>(R.id.registerEmail)
        val passwordLayout = findViewById<TextInputLayout>(R.id.registerPassword)
        val confirmPasswordLayout = findViewById<TextInputLayout>(R.id.registerConfirmPassword)

        val enterEmailAddress = findViewById<TextInputEditText>(R.id.email)
        val enterPassword = findViewById<TextInputEditText>(R.id.password)
        val enterConfirmPassword = findViewById<TextInputEditText>(R.id.confirmPassword)

        enterEmailAddress.doOnTextChanged { text, start, before, count ->
            val emailValid = text.toString()
            if (isEmailValid(emailValid))  {
                emailLayout.error = null
            }
            else {
                emailLayout.error = "Error: Please enter a valid email address."
            }
        }

        ViewCompat.setAccessibilityLiveRegion(emailLayout, ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE)

        enterPassword.doOnTextChanged { text, start, before, count ->
            val passValid = text.toString()
            if (isPassValid(passValid)) {
                passwordLayout.error = null
            }
            else {
                passwordLayout.error = "Error: Make sure password is more than 6 characters long."
            }
        }

        ViewCompat.setAccessibilityLiveRegion(passwordLayout,
            ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE
        )

        enterConfirmPassword.doOnTextChanged { text, start, before, count ->
            val confirmPassValid = text.toString()
            val passValid = passwordLayout.editText?.text.toString()

            if (passValid == confirmPassValid) {
                confirmPasswordLayout.error = null
            }
            else {
                confirmPasswordLayout.error = "Error: Passwords do not match."
            }
        }

        ViewCompat.setAccessibilityLiveRegion(confirmPasswordLayout,
            ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE
        )

        val registerButton = findViewById<Button>(R.id.completeRegistration)
        registerButton.setOnClickListener {
            val emailAddress = enterEmailAddress.text.toString()
            val password = enterPassword.text.toString()

            authentication.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this) { task  ->
                    if (task.isSuccessful) {
                        val registerComplete = Intent(this, Login::class.java)
                        startActivity(registerComplete)
                    }
                    else {
                        unableToRegister()
                    }
                }
        }


        val loginButton = findViewById<Button>(R.id.loginView)
        loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun unableToRegister() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to register account!!")
            .setMessage("Please double check your email and password and make sure that they are appropriately filled. If you have already registered, please move to the login page. If you are still having the same problem than please reach out to us directly via email.")
            .setNegativeButton("Cancel", null)

            .show()
    }

    private fun isPassValid(passValid: String): Boolean {
        val regex = Pattern.compile(
            "^.{6,}"
        )
        return regex.matcher(passValid).matches()
    }

    private fun isEmailValid(emailValid: String): Boolean {
        val regex = Pattern.compile(
            "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"
        )
        return regex.matcher(emailValid).matches()
    }
}