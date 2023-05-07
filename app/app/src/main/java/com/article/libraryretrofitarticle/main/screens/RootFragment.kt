package com.article.libraryretrofitarticle.main.screens

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.article.core.views.BaseFragment
import com.article.core.views.screenViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentMainRootBinding

class RootFragment : BaseFragment(R.layout.fragment_main_root) {

    override val viewModel: RootViewModel by screenViewModel()
    private lateinit var binding: FragmentMainRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainRootBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}