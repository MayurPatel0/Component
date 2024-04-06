package com.example.component.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class RadioButton : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton
    private lateinit var radioButton4: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_radio_button)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        radioGroup = findViewById(R.id.groupLabel)
        radioButton = findViewById(R.id.radio1)
        radioButton1 = findViewById(R.id.radio2)
        radioButton2 = findViewById(R.id.radio3)
        radioButton3 = findViewById(R.id.radio4)
        radioButton4 = findViewById(R.id.radio5)


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