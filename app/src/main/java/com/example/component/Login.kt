package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

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

        val emailLayout = findViewById<TextInputLayout>(R.id.loginEmail)
        val passwordLayout = findViewById<TextInputLayout>(R.id.loginPass)

        val enterEmailAddress = findViewById<TextInputEditText>(R.id.email)
        val enterPassword = findViewById<TextInputEditText>(R.id.password)

        enterEmailAddress.doOnTextChanged { text, start, before, count ->
            val validEmail = text.toString()
            if (isEmailValid(validEmail))  {
                emailLayout.error = null
            }
            else {
                emailLayout.error = "Error: Please enter a valid email address."

            }
        }

        ViewCompat.setAccessibilityLiveRegion(emailLayout, ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE)

        enterPassword.doOnTextChanged { text, start, before, count ->
            val validPass = text.toString()
            if (isPassValid(validPass)) {
                passwordLayout.error = null
            }
            else {
                passwordLayout.error = "Error: Make sure password is more than 6 characters long."
            }
        }

        ViewCompat.setAccessibilityLiveRegion(passwordLayout,
            ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE
        )

        val forgotPassword = findViewById<TextView>(R.id.forgotPassword)
        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        val goToRegisterButton = findViewById<Button>(R.id.registerView)

        goToRegisterButton.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        val completeLoginButton = findViewById<Button>(R.id.completeLogin)

        completeLoginButton.setOnClickListener {
            val emailAddress = enterEmailAddress.text.toString()
            val password = enterPassword.text.toString()

            if (emailAddress.isEmpty() && password.isEmpty()) {
                emptyFields()
            }
            else if (emailAddress.isEmpty()){
                emptyEmailField()
            }
            else if (!isEmailValid(emailAddress)){
                errorEmailField()
            }
            else if (password.isEmpty()){
                emptyPassField()
            }
            else if (!isPassValid(password)){
                errorPassField()
            }
            else {

            authentication.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val loginComplete = Intent(this, MainActivity::class.java)
                        startActivity(loginComplete)
                    } else {
                        userNotFound()
                    }
                }
            }
        }

    }

    private fun errorPassField() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Register!!")
            .setMessage("Please make sure that password field is appropriately filled with a valid password.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun errorEmailField() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Register!!")
            .setMessage("Please make sure that email field is appropriately filled with a valid previously non-registered email.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun emptyPassField() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Login!!")
            .setMessage("Please make sure that password field is appropriately filled, they cannot be left empty.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun emptyEmailField() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Login!!")
            .setMessage("Please make sure that email field is appropriately filled, they cannot be left empty.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun emptyFields() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Login!!")
            .setMessage("Please make sure all fields are appropriately filled, they cannot be left empty.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun userNotFound() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Login!!")
            .setMessage("Please double check your email address and password. If you've forgotten your password, you can reset it via Forgot Password? or if you don't have account move to the register page and register for an account.")
            .setNegativeButton("Cancel", null)

            .show()
    }

    private fun isPassValid(validPass: String): Boolean {
        val regex = Pattern.compile(
            "^.{6,}"
        )
        return regex.matcher(validPass).matches()
    }

    private fun isEmailValid(validEmail: String): Boolean {
        val regex = Pattern.compile(
            "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"
        )
        return regex.matcher(validEmail).matches()
    }
}