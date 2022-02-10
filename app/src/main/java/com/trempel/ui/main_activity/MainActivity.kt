package com.trempel.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.trempel.MyApplication
import com.trempel.R
import com.trempel.databinding.ActivityMainBinding
import com.trempel.setupWithNavController
import javax.inject.Inject

internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private var currentNavController: LiveData<NavController>? = null

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNav()
        firstSignIn()
    }

    private fun firstSignIn() {
        if (!viewModel.isLoggedIn) {
            currentNavController?.value?.navigate(R.id.loginFragment)
        }
    }

    private fun setUpBottomNav() {
        val controller = binding.bottomNav.setupWithNavController(
            navGraphIds = listOf(
                R.navigation.nav_graph_home_page,
                R.navigation.nav_graph_categories,
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