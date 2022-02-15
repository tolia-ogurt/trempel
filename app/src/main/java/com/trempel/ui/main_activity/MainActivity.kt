package com.trempel.ui.main_activity

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.trempel.MyApplication
import com.trempel.R
import com.trempel.core_ui.BaseActivity
import com.trempel.databinding.ActivityMainBinding
import com.trempel.setupWithNavController
import javax.inject.Inject

internal class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private var currentNavController: LiveData<NavController>? = null

    lateinit var navController: NavController

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding.backPressedListener()
        setContentView(binding.root)
        setUpBottomNav()
        firstSignIn()
        setNavigationIcon()
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
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homePageFragment,
                R.id.categoryFragment,
                R.id.bagFragment,
                R.id.favouritesFragment,
                R.id.profileFragment
            ),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        controller.observe(this) {
            binding.toolbar.setupWithNavController(it, appBarConfiguration)
        }
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun ActivityMainBinding.backPressedListener() {
        this.toolbar.setNavigationOnClickListener {
            currentNavController?.value?.popBackStack()
        }
    }

    private fun setNavigationIcon() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view)
        val childFragmentManager = navHostFragment?.childFragmentManager
        childFragmentManager?.addOnBackStackChangedListener {
            binding.toolbar.navigationIcon
                ?.setVisible(childFragmentManager.backStackEntryCount > 0, false)
        }
    }

    override fun showToolbar() {
        binding.toolbar.isVisible = true
    }

    override fun hideToolbar() {
        binding.toolbar.isVisible = false
    }

    override fun showBottomNav() {
        binding.bottomNav.isVisible = true
    }

    override fun hideBottomNav() {
        binding.bottomNav.isVisible = false
    }

    override fun changeTitle(title: String) {
        binding.toolbar.title = title
    }
}