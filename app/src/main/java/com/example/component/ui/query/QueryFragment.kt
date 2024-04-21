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
import androidx.cardview.widget.CardView
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
    private var imageUrl: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[QueryViewModel::class.java]

        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        emailID = root.findViewById(R.id.email)
        queryText = root.findViewById(R.id.feedback)
        queryDatabase = FirebaseFirestore.getInstance()
        queryImageStorage = FirebaseStorage.getInstance()

        val imageChooser = root.findViewById<Button>(R.id.chooseImage)
        imageChooser.setOnClickListener {
            uploadImage()
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

    private fun cannotSubmit() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Unable to Submit QUERY!!")
            .setMessage("Please make sure that you have filled the email and query text fields, as they are required and you wouldn't be able to submit this query.")
            .setNegativeButton("Cancel", null)
            .show()
    }


    private fun uploadImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK) {
            val imageUri = data?.data ?: return
            imageUrl = imageUri.toString()
            imagePick.setImageURI(imageUri)
        }
    }

    private fun uploadStorage() {
        val image = queryImageStorage.reference.child("Images/" + UUID.randomUUID().toString())
        val uploadTask = image.putFile(Uri.parse(imageUrl!!))

        uploadTask.addOnSuccessListener {
            image.downloadUrl.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUrl = task.result.toString()
                    queryFirestore(downloadUrl)
                }
                else {
                    uploadFail()
                }
            }
        }

    }

    private fun uploadFail() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Image Upload Failure!!")
            .setMessage("Please try again or alternatively email us your query directly on test123@123.com.")
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun queryFirestore(queryImgURL: String) {

        val emailAddrs = emailID.text.toString().trim()
        val query = queryText.text.toString().trim()

        val queryData = hashMapOf(
            "Image" to queryImgURL,
            "Email Address" to emailAddrs,
            "Query Text" to query
        )

        queryDatabase.collection("Query - Android")
            .add(queryData)
            .addOnSuccessListener {
                emailID.setText("")
                queryText.setText("")
                imageUrl = null
            }

            .addOnFailureListener {

            }

    }


    companion object {
        private const val REQUEST_IMAGE_CODE = 22
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}