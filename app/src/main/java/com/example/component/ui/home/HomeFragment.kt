package com.example.component.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.component.databinding.FragmentHomeBinding
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.component.R
import com.example.component.datePicker
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


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var listView: ListView
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        listView = root.findViewById(R.id.text_home)
        val items = arrayOf("Button", "Non-Interactive Elements", "Checkbox", "Switch", "Table", "List", "Links", "Radio", "Loading", "Input", "Slider", "Dialog", "Dropdown", "Picker", "Progress", "Segmented Control", "Tabs", "Banner", "Date Picker")

        // Replace with your data
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()

            when (selectedItem) {
                "Checkbox" -> {
                    val intent = Intent(requireActivity(), CheckBoxView::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Progress" -> {
                    val intent = Intent(requireActivity(), Progress::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Dropdown" -> {
                    val intent = Intent(requireActivity(), SelectDropdown::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "List" -> {
                    val intent = Intent(requireActivity(), List::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Loading" -> {
                    val intent = Intent(requireActivity(), Loading::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Picker" -> {
                    val intent = Intent(requireActivity(), SelectPicker::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Slider" -> {
                    val intent = Intent(requireActivity(), Sliders::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Links" -> {
                    val intent = Intent(requireActivity(), LinkView::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Input" -> {
                    val intent = Intent(requireActivity(), Input::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Radio" -> {
                    val intent = Intent(requireActivity(), RadioButton::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Dialog" -> {
                    val intent = Intent(requireActivity(), ModalDialog::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Button" -> {
                    val intent = Intent(requireActivity(), ButtonView::class.java)
                    startActivity(intent)
                }
            }
            when  (selectedItem) {
                "Tabs" -> {
                    val intent = Intent(requireActivity(), TabControl::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Switch" -> {
                    val intent = Intent(requireActivity(), toggleSwitch::class.java)
                    startActivity(intent)
                }
            }
            when (selectedItem) {
                "Banner" -> {
                    val intent = Intent(requireActivity(), Banner::class.java)
                    startActivity(intent)
                }
            }
            when(selectedItem) {
                "Non-Interactive Elements" -> {
                    val intent = Intent(requireActivity(), NonInteractive::class.java)
                    startActivity(intent)
                }
            }
            when(selectedItem) {
                "Date Picker" -> {
                    val intent = Intent(requireActivity(), datePicker::class.java)
                    startActivity(intent)
                }
            }
            when(selectedItem) {
                "Segmented Control" -> {
                    val intent = Intent(requireActivity(), Carousel::class.java)
                    startActivity(intent)
                }
            }
        }

        searchView = root.findViewById(R.id.Search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(updatedList: String?): Boolean {
                adapter.filter.filter(updatedList)
                return false
            }
        } )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}