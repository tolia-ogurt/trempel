package com.example.trempel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trempel.databinding.CategoryFragmentBinding

internal class CategoryFragment : Fragment(R.layout.category_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CategoryFragmentBinding.inflate(inflater, container, false)
        binding.ivCategory.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_pdpFragment)
        }
        return binding.root
    }
}