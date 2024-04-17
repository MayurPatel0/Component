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
    private lateinit var uploadPick: CardView

    private lateinit var queryDatabase: FirebaseFirestore
    private lateinit var emailID: TextInputEditText
    private lateinit var queryText: TextInputEditText
    private lateinit var queryImageStorage: FirebaseStorage
    private var imageUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val queryViewModel =
            ViewModelProvider(this)[QueryViewModel::class.java]

        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        queryDatabase = FirebaseFirestore.getInstance()
        queryImageStorage = FirebaseStorage.getInstance()

        uploadPick = root.findViewById(R.id.imagePicker)

        uploadPick.setOnClickListener {
            imagePick = root.findViewById(R.id.imagePick)
            uploadImage()
        }

        val submitBtn = root.findViewById<Button>(R.id.submitQuery)

        submitBtn.setOnClickListener {
            uploadStorage()
        }

        return root
    }



    private fun uploadImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK) {
            val imageUri = data?.data ?: return
            imagePick.setImageURI(imageUri)
        }
    }

    private fun uploadStorage() {
        val image = queryImageStorage.reference.child("Query Images/${UUID.randomUUID()}").putFile(imageUri!!)

        image.addOnSuccessListener { task ->
            task.storage.downloadUrl.addOnSuccessListener { uri ->
                queryFirestore(uri.toString())
            }
        }

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
            }

            .addOnFailureListener {

            }

    }


    companion object {
        private const val REQUEST_IMAGE_CODE = 100
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}