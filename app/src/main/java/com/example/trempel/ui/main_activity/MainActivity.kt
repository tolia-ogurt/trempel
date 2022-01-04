package com.example.trempel.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.trempel.MyApplication
import com.example.trempel.R
import javax.inject.Inject

internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.container_view)
        if (viewModel.isLoggedIn) {
            navController.navigate(R.id.categoryFragment)
        } else {
            navController.navigate(R.id.loginFragment)
        }
    }
}