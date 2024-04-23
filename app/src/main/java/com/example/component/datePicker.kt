package com.example.component

import android.annotation.SuppressLint
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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

class datePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_date_picker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.dpToolbar)
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

        //Code bottom sheet triggering button
        val codeAccessButton = findViewById<Button>(R.id.dpCode)
        //Show the code bottom sheet
        codeAccessButton.setOnClickListener { codeBottomSheet() }
    }

    @SuppressLint("InflateParams")
    private fun codeBottomSheet() {
        val dpBottomSheetDialog = BottomSheetDialog(this)
        val dpBottomSheetDialogView = layoutInflater.inflate(R.layout.dp_bottom_sheet, null)
        dpBottomSheetDialog.setContentView(dpBottomSheetDialogView)
        val closeDialog = dpBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { dpBottomSheetDialog.dismiss() }

        val check1 = dpBottomSheetDialogView.findViewById<CheckBox>(R.id.dpCode1)
        val check2 = dpBottomSheetDialogView.findViewById<CheckBox>(R.id.dpCode2)
        val check3 = dpBottomSheetDialogView.findViewById<CheckBox>(R.id.dpCode3)
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

        dpBottomSheetDialog.show()


    }
}