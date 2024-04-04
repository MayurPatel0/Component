package com.example.component.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ModalDialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modal_dialog)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val modalDialogButton = findViewById<Button>(R.id.buttonDialog)
        modalDialogButton.setOnClickListener {
            dialog()
        }
    }

    private fun dialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Are you sure you want to Submit?")
            .setMessage("By submitting this, you wouldn't be able to return back. If you want to abort, press the cancel button now!")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Continue"){ dialog, which ->

            }

        .show()

    }
}