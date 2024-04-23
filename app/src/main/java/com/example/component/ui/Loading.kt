package com.example.component.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
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

class Loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.loadingToolbar)
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

        val progressbar = findViewById<ProgressBar>(R.id.progress)

        val loadingCodeButton = findViewById<Button>(R.id.loadingCode)
        loadingCodeButton.setOnClickListener { loadingCodeBottomSheet() }

    }

    private fun loadingCodeBottomSheet() {
        val loadingBottomSheetDialog = BottomSheetDialog(this)
        val loadingBottomSheetDialogView = layoutInflater.inflate(R.layout.loading_bottom_sheet, null)
        loadingBottomSheetDialog.setContentView(loadingBottomSheetDialogView)
        val closeDialog = loadingBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { loadingBottomSheetDialog.dismiss() }
        val check1 = loadingBottomSheetDialogView.findViewById<CheckBox>(R.id.loadingCode1)
        val check2 = loadingBottomSheetDialogView.findViewById<CheckBox>(R.id.loadingCode2)
        val check3 = loadingBottomSheetDialogView.findViewById<CheckBox>(R.id.loadingCode3)

        //Code re-used from Chat-Gpt, when a checkbox is checked, strikethrough a textview.
        //Note. The only difference is the chat-gpt code gave for a particular textview, and we applied here for the checkbox semantic label.
        check1.setOnCheckedChangeListener { _, isChecked ->
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
        loadingBottomSheetDialog.show()
    }
}