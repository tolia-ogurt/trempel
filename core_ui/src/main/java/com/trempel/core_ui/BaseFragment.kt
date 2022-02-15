package com.trempel.core_ui

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val isToolbarVisible: Boolean
    open val isBottomNavVisible: Boolean = true
    open val title: String = ""

    override fun onStart() {
        super.onStart()
        settingsToolbar()
        settingsBottomNav()
    }

    private fun settingsToolbar() {
        with(activity as BaseActivity) {
            changeTitle(this@BaseFragment.title)
            if (isToolbarVisible) showToolbar() else hideToolbar()
        }
    }

    private fun settingsBottomNav() {
        with(activity as BaseActivity) {
            if (isBottomNavVisible) showBottomNav() else hideBottomNav()
        }
    }
}