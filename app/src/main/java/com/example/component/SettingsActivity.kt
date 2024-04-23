package com.example.component

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        //Android Studio generated code as part of the settings view.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userDetails = findViewById<TextView>(R.id.userEmail)

        //Getting authenticated email ID details
        //Guidance for code taken from: [https://firebase.google.com/docs/auth/android/password-auth]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
        val authDetails = FirebaseAuth.getInstance().currentUser
        //If the user is registered, his email ID details is shown.
        if (authDetails == null) {
            userDetails.text = "Not a registered user!!!"
        }
        else {
            userDetails.text = authDetails.email
        }

        val accessibilityStatement = findViewById<TextView>(R.id.statement)
        Linkify.addLinks(accessibilityStatement, Linkify.WEB_URLS)
        accessibilityStatement.setOnClickListener {
            val accessibilityDocument = "https://docs.google.com/document/d/1bxb_UDJsmMCkNNMNojDdGv3FzhdfmgCBHOMEhgFaPE4/edit?usp=sharing"
            val accessibilityURL = Intent(Intent.ACTION_VIEW, Uri.parse(accessibilityDocument))
            startActivity(accessibilityURL)
        }

        //Guidance for code taken from: [https://firebase.google.com/docs/auth/android/password-auth]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
        val logOut = findViewById<TextView>(R.id.logOut)
        logOut.setOnClickListener {
            Firebase.auth.signOut()

            //Once logged out, navigate user to the Splash screen
            val logUserOut = Intent(this, Splash::class.java)
            startActivity(logUserOut)
        }
    }

    //This code is generated as part of the Settings Activity View by Android Studio
    override fun onSupportNavigateUp(): Boolean {

        //Navigating back to Home Screen(MainActivity) is my code.
        val navigateBack = Intent(this, MainActivity::class.java)
        navigateBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(navigateBack)
        return super.onSupportNavigateUp()
    }

    // This code is generated as part of the Settings Activity View by Android Studio.
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}