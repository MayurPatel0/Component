package com.example.component.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.GuestActivity
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

class RadioButton : AppCompatActivity() {

    private lateinit var legend: RadioGroup
    private lateinit var radioBtn1: RadioButton
    private lateinit var radioBtn2: RadioButton
    private lateinit var radioBtn3: RadioButton
    private lateinit var radioBtn4: RadioButton
    private lateinit var radioBtn5: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_radio_button)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //app top bar
        val appTopBar = findViewById<Toolbar>(R.id.radioToolbar)
        setSupportActionBar(appTopBar)

        //firebase authentication initialisation: [https://firebase.google.com/docs/auth/android/start]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
        val authentication = FirebaseAuth.getInstance()
        appTopBar.setNavigationOnClickListener {
            val authUser = authentication.currentUser
            if (authUser == null) {
                val navigateBack = Intent(this, GuestActivity::class.java)
                startActivity(navigateBack)
            }
            else {
                val navigateBack = Intent(this, MainActivity::class.java)
                startActivity(navigateBack)
            }
        }


        //View binding the radio group and buttons from the layout.
        legend = findViewById(R.id.groupLabel)
        radioBtn1 = findViewById(R.id.radio1)
        radioBtn2 = findViewById(R.id.radio2)
        radioBtn3 = findViewById(R.id.radio3)
        radioBtn4 = findViewById(R.id.radio4)
        radioBtn5 = findViewById(R.id.radio5)


        val radioButtonCode = findViewById<Button>(R.id.radioCode)
        radioButtonCode.setOnClickListener { radioCodeBottomSheet() }
    }

    private fun radioCodeBottomSheet() {
        val radioBottomSheetDialog = BottomSheetDialog(this)
        val radioBottomSheetDialogView = layoutInflater.inflate(R.layout.radio_bottom_sheet, null)
        radioBottomSheetDialog.setContentView(radioBottomSheetDialogView)
        val closeDialog = radioBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { radioBottomSheetDialog.dismiss() }
        radioBottomSheetDialog.show()
    }
}