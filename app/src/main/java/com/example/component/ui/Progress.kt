package com.example.component.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class Progress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_progress)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val progressBar = findViewById<ProgressBar>(R.id.progressBar3)

        val progressCodeButton = findViewById<Button>(R.id.progressCode)
        progressCodeButton.setOnClickListener { progressCodeBottomSheet() }
    }

    private fun progressCodeBottomSheet() {
        val progressBottomSheetDialog = BottomSheetDialog(this)
        val progressBottomSheetDialogView = layoutInflater.inflate(R.layout.progress_bottom_sheet, null)
        progressBottomSheetDialog.setContentView(progressBottomSheetDialogView)
        val closeDialog = progressBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { progressBottomSheetDialog.dismiss() }
        progressBottomSheetDialog.show()
    }
}