package com.article.libraryretrofitarticle.spread.screens.home

import android.os.Bundle
import android.view.View
import com.article.core.views.BaseFragment
import com.article.core.views.HasCustomTitle
import com.article.core.views.screenViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentSpreadHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_spread_home), HasCustomTitle {

    override val viewModel: HomeViewModel by screenViewModel()
    private lateinit var binding: FragmentSpreadHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSpreadHomeBinding.bind(view)

        binding.launchAuthButton.setOnClickListener { viewModel.launchAuthScreen(this@HomeFragment) }
        binding.launchRegistrationButton.setOnClickListener { viewModel.launchRegistration(this@HomeFragment) }
    }

    override fun getScreenTitle(): String = getString(R.string.home_spread_title)
}