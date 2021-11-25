package com.example.trempel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class PdpFragment:Fragment() {
    lateinit var productDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.pdp_fragment, container, false)
        productDescription = rootView.findViewById(R.id.productDescription)
        return rootView
    }

}