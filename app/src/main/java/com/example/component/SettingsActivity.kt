package com.example.component

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val userDetails = findViewById<TextView>(R.id.userEmail)
        val authDetails = FirebaseAuth.getInstance().currentUser
        if (authDetails != null) {
            userDetails.text = authDetails.email
        }

        val logOut = findViewById<TextView>(R.id.logOut)
        logOut.setOnClickListener {
            Firebase.auth.signOut()

            val logUserOut = Intent(this, Splash::class.java)
            startActivity(logUserOut)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigateBack = Intent(this, MainActivity::class.java)
        navigateBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(navigateBack)
        return super.onSupportNavigateUp()
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}