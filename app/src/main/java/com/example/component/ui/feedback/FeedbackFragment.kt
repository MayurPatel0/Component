package com.example.component.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.component.R
import com.example.component.databinding.FragmentFeedbackBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class FeedbackFragment : Fragment() {

    private var _binding: FragmentFeedbackBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var feedbackDatabase: FirebaseFirestore

    private lateinit var componentName: TextInputEditText
    private lateinit var feedbackText: TextInputEditText
    private lateinit var feedbackViewModel: FeedbackViewModel
    private lateinit var rating: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Android Studio generated code boilerplate.
        feedbackViewModel =
            ViewModelProvider(this)[FeedbackViewModel::class.java]

        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root


        feedbackDatabase = FirebaseFirestore.getInstance()

        rating = root.findViewById(R.id.autoCompleteTextView)
        val ratings = arrayOf(
            "0/5 - Poor",
            "1/5 - Bad",
            "2/5 - Fair",
            "3/5 - Good",
            "4/5 - Very Good",
            "5/5 - Excellent"
        )

        val ratingAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, ratings)
        rating.setAdapter(ratingAdapter)

        componentName = root.findViewById(R.id.component)
        feedbackText = root.findViewById(R.id.feedback)

        val submitBtn = root.findViewById<Button>(R.id.submitFeedback)

        submitBtn.setOnClickListener {
            feedbackFirestore()
        }

        return root
    }

    private fun feedbackFirestore() {
        val Component = componentName.text.toString().trim()
        val Rating = rating.text.toString().trim()
        val Feedback = feedbackText.text.toString().trim()

        val feedbackData = hashMapOf(
            "Component" to Component,
            "Rating" to Rating,
            "Feedback Text" to Feedback
        )

        feedbackDatabase.collection("Feedback - Android")
            .add(feedbackData)
            .addOnSuccessListener {

                componentName.setText("")
                rating.setText("")
                feedbackText.setText("")
            }

            .addOnFailureListener {

            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}