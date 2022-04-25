package com.trempel.favorites.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.favorites.databinding.FavoritesFragmentBinding
import com.trempel.core_ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoritesFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: FavoritesViewModel
    override val isToolbarVisible: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FavoritesFragmentBinding.inflate(layoutInflater).apply {
            this.viewModel = this@FavoritesFragment.viewModel
            this.lifecycleOwner = this@FavoritesFragment
        }.also {
            this.viewLifecycleOwner.lifecycle.addObserver(viewModel)
        }.root
    }
}