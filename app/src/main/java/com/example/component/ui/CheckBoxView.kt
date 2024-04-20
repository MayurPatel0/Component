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
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class CheckBoxView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_check_box_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.checkboxToolbar)
        setSupportActionBar(appTopBar)

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
        }

        val checkBox1: CheckBox = findViewById(R.id.checkBox)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val checkBox3: CheckBox = findViewById(R.id.checkBox3)
        val checkBox4: CheckBox = findViewById(R.id.checkBox4)
        val checkBox5: CheckBox = findViewById(R.id.checkBox5)

        val checkboxCodeButton = findViewById<Button>(R.id.checkboxCode)
        checkboxCodeButton.setOnClickListener { checkboxCodeBottomSheet() }
    }

    private fun checkboxCodeBottomSheet() {
        val checkboxBottomSheetDialog = BottomSheetDialog(this)
        val checkboxBottomSheetDialogView = layoutInflater.inflate(R.layout.checkbox_bottom_sheet, null)
        checkboxBottomSheetDialog.setContentView(checkboxBottomSheetDialogView)
        val closeDialog = checkboxBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { checkboxBottomSheetDialog.dismiss() }
        val check1 = checkboxBottomSheetDialogView.findViewById<CheckBox>(R.id.checkCode1)
        val check2 = checkboxBottomSheetDialogView.findViewById<CheckBox>(R.id.checkCode2)
        val check3 = checkboxBottomSheetDialogView.findViewById<CheckBox>(R.id.checkCode3)
        val check4 = checkboxBottomSheetDialogView.findViewById<CheckBox>(R.id.checkCode4)
        val check5 = checkboxBottomSheetDialogView.findViewById<CheckBox>(R.id.checkCode5)
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
        check4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                check4.paintFlags = check4.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else {
                check4.paintFlags = check4.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        check5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                check5.paintFlags = check5.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else {
                check5.paintFlags = check5.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        checkboxBottomSheetDialog.show()
    }
}