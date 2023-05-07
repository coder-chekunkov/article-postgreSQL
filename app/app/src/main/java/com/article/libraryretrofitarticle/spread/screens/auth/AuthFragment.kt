package com.article.libraryretrofitarticle.spread.screens.auth

import android.os.Bundle
import android.view.View
import com.article.core.views.BaseFragment
import com.article.core.views.HasCustomTitle
import com.article.core.views.screenViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentSpreadAuthBinding
import com.article.libraryretrofitarticle.utils.isBlank

class AuthFragment : BaseFragment(R.layout.fragment_spread_auth), HasCustomTitle {

    override val viewModel: AuthViewModel by screenViewModel()
    private lateinit var binding: FragmentSpreadAuthBinding
    private lateinit var mView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSpreadAuthBinding.bind(view)
        mView = view

        binding.signInButton.setOnClickListener { signIn() }
    }

    private fun signIn() {
        if (checkInputLayouts()) viewModel.authorization(
            view = mView,
            email = binding.emailAuthEditText.text.toString(),
            password = binding.passwordAuthEditText.text.toString()
        )
    }

    private fun checkInputLayouts(): Boolean {
        with(binding) {
            val isEmail = valueInputEmailAuth.isBlank(emailAuthEditText)
            val isPassword = valueInputPasswordAuth.isBlank(emailAuthEditText)

            return isEmail && isPassword
        }
    }

    override fun getScreenTitle(): String = getString(R.string.auth_title)
}