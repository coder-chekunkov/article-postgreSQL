package com.article.libraryretrofitarticle.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.article.core.ActivityScopeViewModel
import com.article.core.navigator.IntermediateNavigator
import com.article.core.navigator.NavigatorExecutor
import com.article.core.utils.viewModelCreator
import com.article.core.views.FragmentHolder
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.ActivityMainBinding
import com.cdr.core.uiactions.AndroidUiActions

class MainActivity : AppCompatActivity(), FragmentHolder {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigator: NavigatorExecutor
    private val viewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            navigator = IntermediateNavigator(),
            uiActions = AndroidUiActions(this),
            dependencies = getApplicationDependencies()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dependencies.initDependencies(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val navController = createNavigationController()

        navigator = NavigatorExecutor(
            activity = this,
            navController = navController,
            fragmentContainer = R.id.fragmentContainer,
            toolbar = binding.toolbar,
            defaultTitle = getString(R.string.app_name),
            topLevelDestinations = createListOfTopLevelDestination()
        )

        navigator.onCreate()
    }

    override fun onResume() {
        super.onResume()
        viewModel.navigator.setTarget(navigator)
    }

    override fun onPause() {
        super.onPause()
        viewModel.navigator.setTarget(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        notifyScreenUpdates()
        return true
    }

    override fun onSupportNavigateUp(): Boolean = navigator.onSupportNavigateUp() || super.onSupportNavigateUp()
    override fun notifyScreenUpdates() = navigator.notifyScreenUpdates()
    override fun getActivityScopeViewModel(): ActivityScopeViewModel = viewModel
    override fun getApplicationDependencies(): List<Any> =
        listOf(Dependencies.userRepository, Dependencies.productsRepository, Dependencies.tokenService)

    private fun createNavigationController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        return navHost.navController
    }

    private fun createListOfTopLevelDestination(): List<Int> = listOf()
}