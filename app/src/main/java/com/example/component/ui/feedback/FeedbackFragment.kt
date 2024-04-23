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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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


        //firebase firestore initialisation, the code logic was taken from here: [https://firebase.google.com/docs/firestore/quickstart]
        //Note: The above is the starter guidelines for implementing firebase firestore in your Android apps.
        feedbackDatabase = FirebaseFirestore.getInstance()

        //Ratings list array
        rating = root.findViewById(R.id.autoCompleteTextView)
        val ratings = arrayOf(
            "0/5 - Poor",
            "1/5 - Bad",
            "2/5 - Fair",
            "3/5 - Good",
            "4/5 - Very Good",
            "5/5 - Excellent"
        )

        //Populate list data using adapter into a simple dropdown.
        val ratingAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, ratings)
        rating.setAdapter(ratingAdapter)

        //Component and Feedback text fields
        componentName = root.findViewById(R.id.component)
        feedbackText = root.findViewById(R.id.feedback)

        //Submit Feedback
        val submitBtn = root.findViewById<Button>(R.id.submitFeedback)

        //Only submit feedback if the feedback text is not empty, or else show failure.
        submitBtn.setOnClickListener {
            val feedbackValue = feedbackText.text.toString()
            if(feedbackValue.isEmpty()) {
                feedbackFailure()
            }
            else {
                //Submit to firestore
                feedbackFirestore()
            }
        }

        return root
    }

    //MaterialAlertDialogBuilder were coded using the Material 3 (Material Design), original code boilerplate can be found here: [https://github.com/material-components/material-components-android/blob/master/docs/components/Dialog.md
    // Note: Only the Alert dialog implementation logic was learnt as mentioned above, else the Alert Dialogs were built using the learned knowledge of their code boilerplate.
    private fun feedbackFailure() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Unable to Submit FEEDBACK!!")
            .setMessage("Please make sure that you have filled the feedback text field, as it is a required and you wouldn't be able to submit this form without it.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    //Store Form data to firestore
    private fun feedbackFirestore() {
        val component = componentName.text.toString()
        val ratings = rating.text.toString()
        val feedback = feedbackText.text.toString()

        //Code adapted from here: [https://firebase.google.com/docs/firestore/manage-data/add-data]
        //Reused and adapted as per my requirement, also this code is available already as boilerplate code when setting firestore in android studio.
        val feedbackData = hashMapOf(
            "Component" to component,
            "Rating" to ratings,
            "Feedback Text" to feedback
        )

        //Code adapted from here: [https://firebase.google.com/docs/firestore/manage-data/add-data]
        //Reused and adapted as per my requirement, also this code is available already as boilerplate code when setting firestore in android studio.
        //Firestore add data, On success ->
        feedbackDatabase.collection("Feedback - Android")
            .add(feedbackData)
            .addOnSuccessListener {

                //On success clear data
                componentName.setText("")
                rating.setText("")
                feedbackText.setText("")

            }

            //On failure, show the failure message.
            .addOnFailureListener {
                submitFailure()
            }

    }

    //MaterialAlertDialogBuilder were coded using the Material 3 (Material Design), original code boilerplate can be found here: [https://github.com/material-components/material-components-android/blob/master/docs/components/Dialog.md
    // Note: Only the Alert dialog implementation logic was learnt as mentioned above, else the Alert Dialogs were built using the learned knowledge of their code boilerplate.
    private fun submitFailure() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Unable to Submit FEEDBACK!!")
            .setMessage("Please try again, and if you see this message again, please try after some time.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    //Code generated as part of the boilerplate code of Navigation Drawer Activity views within Android Studio
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}