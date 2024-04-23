package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
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
        //inflating the layout
        setContentView(R.layout.activity_register_page)

        //firebase authentication intialisation, the code logic was taken from here: [https://firebase.google.com/docs/auth/android/start]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
        authentication = FirebaseAuth.getInstance()

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val backToSplash = Intent(this, Splash::class.java)
            startActivity(backToSplash)
        }

        //Referencing the input fields edit and the layout by their ID's from XML.
        val emailLayout = findViewById<TextInputLayout>(R.id.registerEmail)
        val passwordLayout = findViewById<TextInputLayout>(R.id.registerPassword)
        val confirmPasswordLayout = findViewById<TextInputLayout>(R.id.registerConfirmPassword)

        val enterEmailAddress = findViewById<TextInputEditText>(R.id.email)
        val enterPassword = findViewById<TextInputEditText>(R.id.password)
        val enterConfirmPassword = findViewById<TextInputEditText>(R.id.confirmPassword)

        // Validating email input field.
        enterEmailAddress.doOnTextChanged { text, start, before, count ->
            val emailValid = text.toString()
            //
            if (validEmail(emailValid))  {
                emailLayout.error = null
            }
            else {
                emailLayout.error = "Error: Please enter a valid email address."
            }
        }

        // Code Adapted from: [https://appt.org/en/docs/android/samples/accessibility-live-region]
        //From the above, only took guidance on achieving live region for error notification.
        ViewCompat.setAccessibilityLiveRegion(emailLayout, ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE)

        //validating password input field.
        enterPassword.doOnTextChanged { text, start, before, count ->
            val passValid = text.toString()
            //
            if (validPass(passValid)) {
                passwordLayout.error = null
            }
            else {
                passwordLayout.error = "Error: Password is less than 6 characters."
            }
        }

        // Code Adapted from: [https://appt.org/en/docs/android/samples/accessibility-live-region]
        //From the above, only took guidance on achieving live region for error notification.
        ViewCompat.setAccessibilityLiveRegion(passwordLayout,
            ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE
        )

        //validating confirm password field.
        enterConfirmPassword.doOnTextChanged { text, start, before, count ->
            val confirmPassValid = text.toString()
            //
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

        val loginButton = findViewById<Button>(R.id.loginView)
        loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val registerButton = findViewById<Button>(R.id.completeRegistration)
        registerButton.setOnClickListener {
            val emailAddress = enterEmailAddress.text.toString()
            val password = enterPassword.text.toString()
            val confirmPass = enterConfirmPassword.text.toString()

            //if all the required fields are empty, show alert for empty fields.
            if (emailAddress.isEmpty() && password.isEmpty() && confirmPass.isEmpty()) {
                emptyFields()
            }
            //if email address field is empty or invalid, show alert for empty email field or invalid email field respectively.
            else if (emailAddress.isEmpty()){
                emptyEmailField()
            }
            else if (!validEmail(emailAddress)){
                errorEmailField()
            }
            //if password field is empty or invalid, show alert for empty password field or invalid password field respectively.
            else if (password.isEmpty()){
                emptyPassField()
            }
            else if (!validPass(password)){
                errorPassField()
            }
            //if confirm password is empty or does not match the password from password field, show alert respectively.
            else if (confirmPass.isEmpty()){
                emptyPassField()
            }
            else if (password != confirmPass){
                errorPass()
            }
            // else, if all the above conditions are false, register the user.
            else {

                //code logic adapted from: [https://androidknowledge.com/login-signup-android-firebase-auth-kotlin/], further information can be found in appendix.
                // Only Adapted the logic on how to authenticate with email and password, also learnt from original firebase authentication references: [https://firebase.google.com/docs/auth/android/password-auth]
               // Original code: From original code, only adapted how to register a user and what to do on success.
                authentication.createUserWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val registerComplete = Intent(this, Login::class.java)
                            startActivity(registerComplete)
                        } else {
                            //Error Alert when the user cannot be registered, due to multiple reasons such as already registered or network problem, or authentication broke down.
                            unableToRegister()
                        }
                    }

            }
        }
    }


    //All the MaterialAlertDialogBuilder were coded using the Material 3 (Material Design), original code boilerplate can be found here: [https://github.com/material-components/material-components-android/blob/master/docs/components/Dialog.md
    // Note: Only the Alert dialog implementation logic was learnt as mentioned above, else the Alert Dialogs were built using the learned knowledge of their code boilerplate.
    private fun errorPass() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Register!!")
            .setMessage("Please make sure that confirm password and password matches.")
            .setNegativeButton("Cancel", null)
            .show()
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
            .setTitle("Unable to Register!!")
            .setMessage("Please make sure that both password and confirm password fields are appropriately filled, they cannot be left empty.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun emptyEmailField() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Register!!")
            .setMessage("Please make sure that email field is appropriately filled, they cannot be left empty.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun emptyFields() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to Register!!")
            .setMessage("Please make sure that all fields are appropriately filled including confirm password field, they cannot be left empty.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun unableToRegister() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Unable to register account!!")
            .setMessage("Please double check your email and password and make sure that they are appropriately filled. If you have already registered, please move to the login page. If you are still having the same problem than please reach out to us directly via email.")
            .setNegativeButton("Cancel", null)

            .show()
    }

    //The function code for validating password was adapted from chat-gpt.
    //Full details on the re-use and adapt is in re-use appendix
    private fun validPass(passValid: String): Boolean {
        val regex = Pattern.compile(
            "^.{6,}"
        )
        return regex.matcher(passValid).matches()
    }

    //The function code for validating email was adapted from chat-gpt.
    //Full details on the re-use and adapt is in re-use appendix
    private fun validEmail(emailValid: String): Boolean {
        val regex = Pattern.compile(
            "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"
        )
        return regex.matcher(emailValid).matches()
    }
}