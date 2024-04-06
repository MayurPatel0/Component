package com.example.component.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog


class List : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.lists)
        val items = arrayOf("This is a list item", "This is also a list item", "And, this is also a list item")

        val listsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = listsAdapter


        val codeAccessButton = findViewById<Button>(R.id.code)

        codeAccessButton.setOnClickListener { codeBottomSheet() }

    }

    private fun codeBottomSheet() {
        val codeSheet = BottomSheetDialog(this)
        val sheetView = layoutInflater.inflate(R.layout.list_bottom_sheet, null)
        codeSheet.setContentView(sheetView)


        val closeDialog = sheetView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { codeSheet.dismiss() }

        codeSheet.show()
    }

}