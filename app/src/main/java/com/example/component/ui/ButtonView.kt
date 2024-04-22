package com.example.component.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
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

class ButtonView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_button_view)
        //Note: this code is automatically generated code boilerplate for fragment class in Android Studio.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Top bar of the page
        val appTopBar = findViewById<Toolbar>(R.id.buttonToolbar)
        setSupportActionBar(appTopBar)

        //firebase authentication initialisation: [https://firebase.google.com/docs/auth/android/start]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
        val authentication = FirebaseAuth.getInstance()

        //If user is authenticated -> MainActivity, else go to GuestActivity.
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

        //Code bottom sheet triggering button
        val codeAccessButton = findViewById<Button>(R.id.buttonCode)
        //Show the code bottom sheet
        codeAccessButton.setOnClickListener { codeBottomSheet() }
    }

    //Code Bottom Sheet
    private fun codeBottomSheet() {
        val buttonBottomSheetDialog = BottomSheetDialog(this)
        val buttonBottomSheetDialogView = layoutInflater.inflate(R.layout.button_bottom_sheet, null)
        buttonBottomSheetDialog.setContentView(buttonBottomSheetDialogView)
        val closeDialog = buttonBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { buttonBottomSheetDialog.dismiss() }
        val check1 = buttonBottomSheetDialogView.findViewById<CheckBox>(R.id.buttonCode1)
        val check2 = buttonBottomSheetDialogView.findViewById<CheckBox>(R.id.buttonCode2)
        val check3 = buttonBottomSheetDialogView.findViewById<CheckBox>(R.id.buttonCode3)
        check1.setOnCheckedChangeListener { _, isChecked ->

            //Code re-used from Chat-Gpt, when a checkbox is checked, strikethrough a textview.
            //Note. The only difference is the chat-gpt code gave for a particular textview, and we applied here for the checkbox semantic label.
            if (isChecked) {
                check1.paintFlags = check1.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else {
                check1.paintFlags = check1.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        check2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                check2.paintFlags = check2.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else {
                check2.paintFlags = check2.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        check3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                check3.paintFlags = check3.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else {
                check3.paintFlags = check3.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        buttonBottomSheetDialog.show()
    }
}