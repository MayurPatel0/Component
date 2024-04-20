package com.example.component.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class ButtonView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_button_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.buttonToolbar)
        setSupportActionBar(appTopBar)

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
        }


        val codeAccessButton = findViewById<Button>(R.id.buttonCode)

        codeAccessButton.setOnClickListener { codeBottomSheet() }
    }

    private fun codeBottomSheet() {
        val buttonBottomSheetDialog = BottomSheetDialog(this)
        val buttonBottomSheetDialogView = layoutInflater.inflate(R.layout.button_bottom_sheet, null)
        buttonBottomSheetDialog.setContentView(buttonBottomSheetDialogView)
        val closeDialog = buttonBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { buttonBottomSheetDialog.dismiss() }
        buttonBottomSheetDialog.show()
    }
}