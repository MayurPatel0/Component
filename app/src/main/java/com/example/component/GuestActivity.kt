package com.example.component

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
import com.example.component.ui.Banner
import com.example.component.ui.Button
import com.example.component.ui.CheckBoxView
import com.example.component.ui.Input
import com.example.component.ui.LinkView
import com.example.component.ui.List
import com.example.component.ui.Loading
import com.example.component.ui.ModalDialog
import com.example.component.ui.NonInteractive
import com.example.component.ui.Progress
import com.example.component.ui.RadioButton
import com.example.component.ui.SelectDropdown
import com.example.component.ui.SelectPicker
import com.example.component.ui.Sliders
import com.example.component.ui.Switch
import com.example.component.ui.TabControl

class GuestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.guestViewToolbar)
        setSupportActionBar(appTopBar)

        val componentListView = findViewById<ListView>(R.id.componentList)
        val componentList = arrayOf("Button", "Non-Interactive Elements", "Checkbox", "Switch", "Table", "List", "Links", "Radio", "Loading", "Input", "Slider", "Dialog", "Dropdown", "Picker", "Progress", "Carousel", "Tabs", "Banner")

        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, componentList)
        componentListView.adapter = listAdapter

        val listSearch = findViewById<SearchView>(R.id.componentSearch)
        listSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })

        componentListView.setOnItemClickListener { parent, _, position, _ ->
            val navigateComponent = parent.getItemAtPosition(position).toString()
            when (navigateComponent) {
                "Checkbox" -> startActivity(Intent(this, CheckBoxView::class.java))
                "Progress" -> startActivity(Intent(this, Progress::class.java))
                "Dropdown" -> startActivity(Intent(this, SelectDropdown::class.java))
                "List" -> startActivity(Intent(this, List::class.java))
                "Loading" -> startActivity(Intent(this, Loading::class.java))
                "Picker" -> startActivity(Intent(this, SelectPicker::class.java))
                "Slider" -> startActivity(Intent(this, Sliders::class.java))
                "Links" -> startActivity(Intent(this, LinkView::class.java))
                "Input" -> startActivity(Intent(this, Input::class.java))
                "Radio" -> startActivity(Intent(this, RadioButton::class.java))
                "Dialog" -> startActivity(Intent(this, ModalDialog::class.java))
                "Button" -> startActivity(Intent(this, Button::class.java))
                "Tabs" -> startActivity(Intent(this, TabControl::class.java))
                "Switch" -> startActivity(Intent(this, Switch::class.java))
                "Banner" -> startActivity(Intent(this, Banner::class.java))
                "Non-Interactive Elements" -> startActivity(Intent(this, NonInteractive::class.java))
            }

            }
        }

    }
