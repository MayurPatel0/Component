package com.example.component.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class SelectDropdown : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_dropdown)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.dropdownToolbar)
        setSupportActionBar(appTopBar)

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
        }

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val items = arrayOf(
            "Woman",
            "Man",
            "Transgender",
            "Non-binary",
            "No response"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        autoCompleteTextView.setAdapter(adapter)

        val dropdownCodeButton = findViewById<Button>(R.id.dropdownCode)
        dropdownCodeButton.setOnClickListener { dropdownCodeBottomSheet() }

    }


    private fun dropdownCodeBottomSheet() {
        val dropdownBottomSheetDialog = BottomSheetDialog(this)
        val dropdownBottomSheetDialogView = layoutInflater.inflate(R.layout.dropdown_bottom_sheet, null)
        dropdownBottomSheetDialog.setContentView(dropdownBottomSheetDialogView)
        val closeDialog = dropdownBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { dropdownBottomSheetDialog.dismiss() }
        val check1 = dropdownBottomSheetDialogView.findViewById<CheckBox>(R.id.dropdownCode1)
        val check2 = dropdownBottomSheetDialogView.findViewById<CheckBox>(R.id.dropdownCode2)
        val check3 = dropdownBottomSheetDialogView.findViewById<CheckBox>(R.id.dropdownCode3)
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
        dropdownBottomSheetDialog.show()
    }
}