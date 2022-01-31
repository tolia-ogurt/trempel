package com.example.trempel.ui.home_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trempel.R
import com.example.trempel.databinding.HomePageFragmentBinding

internal class HomePageFragment : Fragment(R.layout.home_page_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return HomePageFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@HomePageFragment
        }.also {
            it.setOnClickListenerHomeBtn()
        }.root
    }

    private fun HomePageFragmentBinding.setOnClickListenerHomeBtn() {
        btnToCategories.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_categoryFragment)
        }
    }
}