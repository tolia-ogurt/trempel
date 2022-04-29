package com.trempel.core_ui

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun changeTitle(title: String)
    abstract fun showToolbar()
    abstract fun hideToolbar()
    abstract fun showBottomNav()
    abstract fun hideBottomNav()
}
