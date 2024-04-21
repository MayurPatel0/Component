package com.example.component.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ListView
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

        val appTopBar = findViewById<Toolbar>(R.id.listToolbar)
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
        val check1 = sheetView.findViewById<CheckBox>(R.id.listCode1)
        val check2 = sheetView.findViewById<CheckBox>(R.id.listCode2)
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
        codeSheet.show()
    }

}