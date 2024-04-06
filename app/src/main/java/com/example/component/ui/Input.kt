package com.example.component.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class Input : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputCodeButton = findViewById<Button>(R.id.inputCode)

        inputCodeButton.setOnClickListener { inputCodeBottomSheet() }
    }

    private fun inputCodeBottomSheet() {
        val inputBottomSheetDialog = BottomSheetDialog(this)
        val inputBottomSheetDialogView = layoutInflater.inflate(R.layout.input_bottom_sheet, null)
        inputBottomSheetDialog.setContentView(inputBottomSheetDialogView)
        val closeDialog = inputBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { inputBottomSheetDialog.dismiss() }
        inputBottomSheetDialog.show()
    }
}