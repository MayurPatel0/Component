package com.example.component

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Calendar

class datePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_date_picker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val picker = findViewById<DatePicker>(R.id.datePicker)
        val dateCalendar = Calendar.getInstance()
        picker.init(dateCalendar.get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH),
            dateCalendar.get(Calendar.DAY_OF_MONTH) ) { _, _, _, _ ->
        }



        val codeAccessButton = findViewById<Button>(R.id.dpCode)

        codeAccessButton.setOnClickListener { codeBottomSheet() }
    }

    @SuppressLint("InflateParams")
    private fun codeBottomSheet() {
        val dpBottomSheetDialog = BottomSheetDialog(this)
        val dpBottomSheetDialogView = layoutInflater.inflate(R.layout.dp_bottom_sheet, null)
        dpBottomSheetDialog.setContentView(dpBottomSheetDialogView)
        val closeDialog = dpBottomSheetDialogView.findViewById<ImageView>(R.id.close)
        closeDialog.setOnClickListener { dpBottomSheetDialog.dismiss() }
        dpBottomSheetDialog.show()
    }
}