package com.example.component.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class Sliders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sliders)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.sliderToolbar)
        setSupportActionBar(appTopBar)

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
        }

        val slider = findViewById<SeekBar>(R.id.seekBar)

        val sliderCodeButton = findViewById<Button>(R.id.sliderCode)
        sliderCodeButton.setOnClickListener { sliderCodeBottomSheet() }
    }

    private fun sliderCodeBottomSheet() {
        val sliderBottomSheetDialog = BottomSheetDialog(this)
        val sliderBottomSheetDialogView = layoutInflater.inflate(R.layout.slider_bottom_sheet, null)
        sliderBottomSheetDialog.setContentView(sliderBottomSheetDialogView)
        val closeDialog = sliderBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { sliderBottomSheetDialog.dismiss() }
        sliderBottomSheetDialog.show()
    }
}