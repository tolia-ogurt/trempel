package com.example.trempel

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.File
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.media.ImageReader
import android.net.Uri
import android.provider.MediaStore


class PdpFragment : Fragment() {

    private lateinit var iVProduct: ImageView

    private var requestPermissionLauncher: ActivityResultLauncher<String>? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    readFile()
                } else {
                    createAlertDialog()
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.pdp_fragment, container, false)
        iVProduct = rootview.findViewById(R.id.iV_product)
        iVProduct.setOnClickListener {
            hasReadExternalStoragePermission()
        }
        return rootview
    }

        private fun hasReadExternalStoragePermission() {
        requestPermissionLauncher
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.e("Permission request", "GRANTED")
                readFile()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                Log.e("Permission request", "DENIED")
            }
            else -> {
                createAlertDialog()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            val selectedImage: Uri? = data?.getData()
            iVProduct.setImageURI(selectedImage)
        }
    }

    private fun readFile() {
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(
            pickPhoto,
            1
        )
    }

    private fun createAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission needed")
            .setMessage(
                "this permission is necessary for the further" +
                        "correct operation of the application"
            )
            .setPositiveButton("OK") { dialog, id ->
                requestPermissionLauncher?.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                dialog.cancel()
            }
            .show()
    }
}