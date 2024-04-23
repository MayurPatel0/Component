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
import com.example.component.ui.ButtonView
import com.example.component.ui.Carousel
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
import com.example.component.ui.TabControl
import com.example.component.ui.toggleSwitch

class GuestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guest)

        //Code Generated as part of code boilerplate.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appTopBar = findViewById<Toolbar>(R.id.guestViewToolbar)
        setSupportActionBar(appTopBar)

        val componentListView = findViewById<ListView>(R.id.componentList)

        //Initialising an array list with components.
        val componentList = arrayOf("Button", "Non-Interactive Elements", "Checkbox", "Switch", "List", "Links", "Radio", "Loading", "Input", "Slider", "Dialog", "Dropdown", "Picker", "Progress", "Carousel", "Tabs", "Banner")


        // Code Adapted: Learnt as part of kotlin tutorial -> [https://www.tutorialspoint.com/how-to-use-searchview-in-android-kotlin]
        // Only Adapted on how to display a listview with array of lists with a simple list layout.
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, componentList)
        componentListView.adapter = listAdapter

        val listSearch = findViewById<SearchView>(R.id.componentSearch)


        //Filtering the list on text change, this is the same as MainActivity.kt
        // Code Adapted: Learnt as part of kotlin tutorial -> [https://www.tutorialspoint.com/how-to-use-searchview-in-android-kotlin]
        listSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(stringList: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(filteredList: String?): Boolean {
                listAdapter.filter.filter(filteredList)
                return false
            }
        })

        //When the individual list items are triggered, navigate to the corresponding activity class.
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
                "Button" -> startActivity(Intent(this, ButtonView::class.java))
                "Tabs" -> startActivity(Intent(this, TabControl::class.java))
                "Switch" -> startActivity(Intent(this, toggleSwitch::class.java))
                "Banner" -> startActivity(Intent(this, Banner::class.java))
                "Non-Interactive Elements" -> startActivity(Intent(this, NonInteractive::class.java))
                "Carousel" -> startActivity(Intent(this, Carousel::class.java))
            }

            }
        }

    }
