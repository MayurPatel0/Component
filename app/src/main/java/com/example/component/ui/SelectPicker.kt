package com.example.component.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

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

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
        }

        val spinner = findViewById<Spinner>(R.id.Select_spinner)

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

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values)
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
        pickerBottomSheetDialog.show()
    }
}