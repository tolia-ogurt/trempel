package com.example.trempel

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.content.Intent
import android.provider.MediaStore

class PdpFragment : Fragment() {

    private var iVProduct: ImageView? = null
    private var requestPermissionLauncher: ActivityResultLauncher<String>? = null
    private val isStoragePermissionGranted: Boolean
        get() {
            val currentPermissionStatus = context?.let {
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            return currentPermissionStatus == PackageManager.PERMISSION_GRANTED
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializationRequestPermissionLauncher()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.pdp_fragment, container, false)
        iVProduct = rootview.findViewById(R.id.iv_product)
        iVProduct?.setOnClickListener {
            hasReadExternalStoragePermission()
        }
        return rootview
    }

    private fun hasReadExternalStoragePermission() {
        when {
            isStoragePermissionGranted -> {
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
        if (resultCode == RESULT_OK) {
            data?.data?.let { iVProduct?.setImageURI(it) }
        }
    }

    private fun readFile() {
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(
            pickPhoto,
            REQUEST_CODE
        )
    }

    private fun createAlertDialog() {
        AlertDialog.Builder(context)
            .setTitle(R.string.alert_dialog_title)
            .setMessage(R.string.alert_dialog_message)
            .setPositiveButton(R.string.alert_dialog_title_positive_btn) { dialog, _ ->
                requestPermissionLauncher?.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                dialog.dismiss()
            }.show()
    }

    private fun initializationRequestPermissionLauncher() {
        val activityResultContracts = ActivityResultContracts.RequestPermission()
        requestPermissionLauncher =
            registerForActivityResult(activityResultContracts) { isGranted: Boolean ->
                if (isGranted) readFile() else createAlertDialog()
            }
    }

    companion object {
        private const val REQUEST_CODE = 1
    }

}