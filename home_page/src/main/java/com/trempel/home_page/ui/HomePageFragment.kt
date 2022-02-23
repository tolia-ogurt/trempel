package com.trempel.home_page.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.home_page.R
import com.example.home_page.databinding.HomePageFragmentBinding
import com.trempel.core_ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomePageFragment : BaseFragment() {

    override val isToolbarVisible: Boolean = false

    @Inject
    lateinit var viewModel: HomePageViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return HomePageFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@HomePageFragment
            this.viewModel = this@HomePageFragment.viewModel
        }.also {
            it.setOnClickListenerHomeBtn()
            it.setEditorActionListener()
            observeSearch()
            observeToast()
        }.root
    }

    private fun HomePageFragmentBinding.setOnClickListenerHomeBtn() {
        btnToCategories.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_nav_graph_categories)
        }
    }

    private fun HomePageFragmentBinding.setEditorActionListener() {
        this.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel?.submitText()
                true
            } else {
                false
            }
        }
    }

    private fun observeSearch() {
        viewModel.submitOnSearch.observe(this.viewLifecycleOwner) {
            val action = HomePageFragmentDirections.actionHomePageFragmentToSearchFragment(it)
            view?.findNavController()?.navigate(action)
        }
    }

    private fun observeToast() {
        viewModel.toast.observe(this.viewLifecycleOwner) {
            Toast.makeText(
                this.context,
                resources.getText(R.string.toast_text_search),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
