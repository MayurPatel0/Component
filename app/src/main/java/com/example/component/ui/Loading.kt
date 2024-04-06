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
        loadingBottomSheetDialog.show()
    }
}