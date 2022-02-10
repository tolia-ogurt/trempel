package com.trempel.home_page.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.home_page.R
import com.example.home_page.databinding.HomePageFragmentBinding

internal class HomePageFragment : Fragment() {

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
            findNavController().navigate(R.id.action_homePageFragment_to_nav_graph_categories)
        }
    }
}