package com.example.component.ui.query

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.component.R
import com.example.component.databinding.FragmentQueryBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class QueryFragment : Fragment() {

    private var _binding: FragmentQueryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var imagePick: ImageView

    private lateinit var queryDatabase: FirebaseFirestore
    private lateinit var emailID: TextInputEditText
    private lateinit var queryText: TextInputEditText
    private lateinit var queryImageStorage: FirebaseStorage
    private lateinit var queryImg: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Android Studio generated code boilerplate.
        ViewModelProvider(this)[QueryViewModel::class.java]

        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        emailID = root.findViewById(R.id.email)
        queryText = root.findViewById(R.id.feedback)
        imagePick = root.findViewById(R.id.imagePick)
        queryDatabase = FirebaseFirestore.getInstance()
        queryImageStorage = FirebaseStorage.getInstance()


        val imageChooser = root.findViewById<Button>(R.id.pickImage)
        imageChooser.setOnClickListener {
            selectImage()
        }
        val submitBtn = root.findViewById<Button>(R.id.submitQuery)

        submitBtn.setOnClickListener {
            val queryEmail = emailID.text.toString()
            val query = queryText.text.toString()

            if (queryEmail.isEmpty() && query.isEmpty()) {
                cannotSubmit()
            }
            else {
                uploadStorage()
            }
        }

        return root
    }

    //MaterialAlertDialogBuilder were coded using the Material 3 (Material Design), original code boilerplate can be found here: [https://github.com/material-components/material-components-android/blob/master/docs/components/Dialog.md
    // Note: Only the Alert dialog implementation logic was learnt as mentioned above, else the Alert Dialogs were built using the learned knowledge of their code boilerplate.
    private fun cannotSubmit() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Unable to Submit QUERY!!")
            .setMessage("Please make sure that you have filled the email and query text fields, as they are required and you wouldn't be able to submit this query.")
            .setNegativeButton("Cancel", null)
            .show()
    }


    //Code re-used from Image Picker tutorial: [https://www.youtube.com/watch?v=rLDKb4eagpI]
    //The full code for selecting an image from the media gallery and uploading was re-used.
    //Full details of re-use can be found in the re-use appendix

    //Select Image
    private fun selectImage() {
        val upload = Intent()
        upload.type = "image/*"
        upload.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(upload, 22)
    }
    //Set ImageView to the selected Image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 22 && resultCode == RESULT_OK) {
            queryImg = data?.data!!
            imagePick.setImageURI(queryImg)
        }
    }

    //Code re-used from Gemini(Google) AI chat-bot.
    //I prompted for uploading Image to Firebase Storage and retrieving the URL to further store in firestore.
    // The only parts I have written are highlighted as such
    //Full details on prompt and re-use can be found in the re-use appendix
    private fun uploadStorage() {
        val filename = UUID.randomUUID().toString() + ".jpg"
        val storageRef = queryImageStorage.reference.child("Query - Android/$filename")
        val uploadTask = storageRef.putFile(queryImg)

        uploadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUrl = task.result.storage.downloadUrl
                downloadUrl.addOnSuccessListener { url ->
                    queryFirestore(url.toString())
                }
            }
            //This is not part of the re-used code.
            else {
                uploadFail()
            }
        }

    }

    //MaterialAlertDialogBuilder were coded using the Material 3 (Material Design), original code boilerplate can be found here: [https://github.com/material-components/material-components-android/blob/master/docs/components/Dialog.md
    // Note: Only the Alert dialog implementation logic was learnt as mentioned above, else the Alert Dialogs were built using the learned knowledge of their code boilerplate.
    private fun uploadFail() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Unable to Upload Image!!")
            .setMessage("Please make sure that you have only selected a single image and not live image or video, if you see this message again, please email directly your query!.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    //Parts of the code were re-used, as mentioned above for uploadStorage() function.
    //They were re-used from Gemini(Google) AI chat-bot.
    //Parts re-used and adapted were -> queryImgURL: String, "Image" to queryImgURL,
    //Full details on prompt and re-use can be found in the re-use appendix
    private fun queryFirestore(queryImgURL: String) {

        val emailAddrs = emailID.text.toString().trim()
        val query = queryText.text.toString().trim()

        //Code adapted from here: [https://firebase.google.com/docs/firestore/manage-data/add-data]
        //Reused and adapted as per my requirement, also this code is available already as boilerplate code when setting firestore in android studio.

        val queryData = hashMapOf(
            "Image" to queryImgURL,
            "Email Address" to emailAddrs,
            "Query Text" to query
        )

        //Firestore add data, On success ->
        queryDatabase.collection("Query - Android")
            .add(queryData)
            .addOnSuccessListener {
                emailID.setText("")
                queryText.setText("")
            }

            ////On failure, show the failure message.
            .addOnFailureListener {
                queryFailure()
            }

    }

    //MaterialAlertDialogBuilder were coded using the Material 3 (Material Design), original code boilerplate can be found here: [https://github.com/material-components/material-components-android/blob/master/docs/components/Dialog.md
    // Note: Only the Alert dialog implementation logic was learnt as mentioned above, else the Alert Dialogs were built using the learned knowledge of their code boilerplate.
    private fun queryFailure() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Unable to Submit QUERY!!")
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