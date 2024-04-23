package com.example.component.ui

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
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

class LinkView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_link_view)
        //Note: this code is automatically generated code boilerplate for fragment class in Android Studio.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Top bar of the page
        val appTopBar = findViewById<Toolbar>(R.id.linkToolbar)
        setSupportActionBar(appTopBar)

        //firebase authentication initialisation: [https://firebase.google.com/docs/auth/android/start]
        //Note: The above is the starter guidelines for implementing firebase authentication in your Android apps.
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

        val linksView = findViewById<TextView>(R.id.linksView)

        val linkText = "Click here for Android Developers"
        linksView.linksClickable = true

        val linkCodeButton = findViewById<Button>(R.id.linkCode)
        linkCodeButton.setOnClickListener { linkCodeBottomSheet() }

    }

    private fun linkCodeBottomSheet() {
        val linkBottomSheetDialog = BottomSheetDialog(this)
        val linkBottomSheetDialogView = layoutInflater.inflate(R.layout.link_bottom_sheet, null)
        linkBottomSheetDialog.setContentView(linkBottomSheetDialogView)
        val closeDialog = linkBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { linkBottomSheetDialog.dismiss() }
        val check1 = linkBottomSheetDialogView.findViewById<CheckBox>(R.id.linkCode1)
        val check2 = linkBottomSheetDialogView.findViewById<CheckBox>(R.id.linkCode2)
        val check3 = linkBottomSheetDialogView.findViewById<CheckBox>(R.id.linkCode3)
        val check4 = linkBottomSheetDialogView.findViewById<CheckBox>(R.id.linkCode4)
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
        linkBottomSheetDialog.show()
    }
}