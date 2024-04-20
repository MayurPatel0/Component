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
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

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

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
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