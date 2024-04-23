package com.example.component.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Spinner
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

class SelectPicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_picker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.pickerToolbar)
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

        val spinner = findViewById<Spinner>(R.id.Select_spinner)

        //List values as part of the spinner options
        val values = arrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        )

        //Populate list data using adapter into a spinner dropdown.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, values)
        spinner.adapter = adapter

        val pickerCodeButton = findViewById<Button>(R.id.pickerCode)
        pickerCodeButton.setOnClickListener { pickerCodeBottomSheet() }

    }

    private fun pickerCodeBottomSheet() {
        val pickerBottomSheetDialog = BottomSheetDialog(this)
        val pickerBottomSheetDialogView = layoutInflater.inflate(R.layout.picker_bottom_sheet, null)
        pickerBottomSheetDialog.setContentView(pickerBottomSheetDialogView)
        val closeDialog = pickerBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { pickerBottomSheetDialog.dismiss() }
        val check1 = pickerBottomSheetDialogView.findViewById<CheckBox>(R.id.pickerCode1)
        val check2 = pickerBottomSheetDialogView.findViewById<CheckBox>(R.id.pickerCode2)
        val check3 = pickerBottomSheetDialogView.findViewById<CheckBox>(R.id.pickerCode3)

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
        pickerBottomSheetDialog.show()
    }
}