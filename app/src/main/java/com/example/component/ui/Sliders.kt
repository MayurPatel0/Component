package com.example.component.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.SeekBar
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
        val check1 = sliderBottomSheetDialogView.findViewById<CheckBox>(R.id.sliderCode1)
        val check2 = sliderBottomSheetDialogView.findViewById<CheckBox>(R.id.sliderCode2)
        val check3 = sliderBottomSheetDialogView.findViewById<CheckBox>(R.id.sliderCode3)
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
        sliderBottomSheetDialog.show()
    }
}