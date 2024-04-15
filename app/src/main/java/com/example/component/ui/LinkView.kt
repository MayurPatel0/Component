package com.example.component.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.MainActivity
import com.example.component.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class LinkView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_link_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.linkToolbar)
        setSupportActionBar(appTopBar)

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
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
        linkBottomSheetDialog.show()
    }
}