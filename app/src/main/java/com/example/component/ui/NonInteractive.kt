package com.example.component.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.component.MainActivity
import com.example.component.R

class NonInteractive : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_non_interactive)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.nonIntToolbar)
        setSupportActionBar(appTopBar)

        appTopBar.setNavigationOnClickListener {
            val navigateBack = Intent(this, MainActivity::class.java)
            startActivity(navigateBack)
        }

        val nonInteractList = findViewById<ListView>(R.id.nonIntList)
        val listItems = arrayOf("Non-Text Content", "Headings", "Orientation", "Page Title", "Touch Target", "Language", "Name, Role & Value", "Dynamic Announcements")

        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        nonInteractList.adapter = listAdapter

        val listSearch = findViewById<SearchView>(R.id.listSearch)
        listSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })

        nonInteractList.setOnItemClickListener { parent, _, position, _ ->
            val navigateComponent = parent.getItemAtPosition(position).toString()
            when (navigateComponent) {
                "Non-Text Content" -> startActivity(Intent(this, NonText::class.java))
                "Headings" -> startActivity(Intent(this, Heading::class.java))
                "Orientation" -> startActivity(Intent(this, Orientation::class.java))
                "Page Title" -> startActivity(Intent(this, PageTitle::class.java))
                "Touch Target" -> startActivity(Intent(this, TouchTarget::class.java))
                "Language" -> startActivity(Intent(this, Language::class.java))
                "Name, Role & Value" -> startActivity(Intent(this, NRV::class.java))
                "Dynamic Announcements" -> startActivity(Intent(this, Announcement::class.java))
            }

        }


    }
}