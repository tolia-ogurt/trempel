package com.example.trempel.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.trempel.MyApplication
import com.example.trempel.R
import com.example.trempel.databinding.ActivityMainBinding
import setupWithNavController
import javax.inject.Inject

internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    private var currentNavController: LiveData<NavController>? = null
    private var navController: NavController? = null

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firstSignIn()
        setUpBottomNav()
    }

    private fun firstSignIn() {
        if (!viewModel.isLoggedIn) {
            navController?.navigate(R.id.loginFragment)
        }
    }

    private fun setUpBottomNav() {
        val controller = binding.bottomNav.setupWithNavController(
            navGraphIds = listOf(
                R.navigation.nav_graph_home_page,
                R.navigation.nav_graph_detail,
                R.navigation.nav_grpah_bag,
                R.navigation.nav_graph_favourites,
                R.navigation.nav_graph_profile
            ),
            fragmentManager = supportFragmentManager,
            containerId = R.id.container_view,
            null
        )
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}