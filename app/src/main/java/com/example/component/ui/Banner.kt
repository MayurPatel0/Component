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
import com.google.android.material.snackbar.Snackbar

class Banner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_banner)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.bannerToolbar)
        setSupportActionBar(appTopBar)

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
        }

        val bannerButton = findViewById<Button>(R.id.bannerButton)

        val bannerCodeButton = findViewById<Button>(R.id.bannerCode)

        bannerButton.setOnClickListener {
            val banner = Snackbar.make(it, "This is the Banner Message", Snackbar.LENGTH_LONG).setAction("", null)
            banner.show()
        }

        bannerCodeButton.setOnClickListener {
            bannerCodeBottomSheet()
        }
    }

    private fun bannerCodeBottomSheet() {
        val bannerBottomSheetDialog = BottomSheetDialog(this)
        val bannerBottomSheetDialogView = layoutInflater.inflate(R.layout.banner_bottom_sheet, null)
        bannerBottomSheetDialog.setContentView(bannerBottomSheetDialogView)
        val closeDialog = bannerBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { bannerBottomSheetDialog.dismiss() }
        bannerBottomSheetDialog.show()
    }
}