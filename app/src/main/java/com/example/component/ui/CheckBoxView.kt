package com.example.component.ui

import android.content.Intent
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
        checkboxBottomSheetDialog.show()
    }
}