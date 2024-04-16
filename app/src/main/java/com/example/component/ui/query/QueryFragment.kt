package com.example.component.ui.query

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.component.R
import com.example.component.databinding.FragmentQueryBinding

class QueryFragment : Fragment() {

    private var _binding: FragmentQueryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var imagePick: ImageView
    private lateinit var uploadPick: CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val queryViewModel =
            ViewModelProvider(this)[QueryViewModel::class.java]

        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        uploadPick = root.findViewById(R.id.imagePicker)

        uploadPick.setOnClickListener {
            imagePick = root.findViewById(R.id.imagePick)
            uploadImage()
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

    companion object {
        private const val REQUEST_IMAGE_CODE = 100
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}