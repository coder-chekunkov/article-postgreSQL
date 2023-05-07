package com.article.libraryretrofitarticle.spread.screens.registration

import android.os.Bundle
import android.view.View
import com.article.core.views.BaseFragment
import com.article.core.views.HasCustomTitle
import com.article.core.views.screenViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentSpreadRegistrationBinding
import com.article.libraryretrofitarticle.utils.isBlank
import com.article.libraryretrofitarticle.utils.isEmail
import com.article.libraryretrofitarticle.utils.isTeapot

class RegistrationFragment : BaseFragment(R.layout.fragment_spread_registration), HasCustomTitle {

    override val viewModel: RegistrationViewModel by screenViewModel()
    private lateinit var binding: FragmentSpreadRegistrationBinding
    private lateinit var mView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSpreadRegistrationBinding.bind(view)
        mView = view

        binding.spreadRegistrationSubtitleTextView.setOnClickListener {
            viewModel.launchAuthScreen(this@RegistrationFragment)
        }
        binding.signUpButton.setOnClickListener { signUp() }
    }

    private fun signUp() {
        if (checkInputLayouts()) viewModel.registration(
            view = mView,
            email = binding.emailRegistrationEditText.text.toString(),
            password = binding.passwordRegistrationEditText.text.toString(),
            firstName = binding.nameRegistrationEditText.text.toString(),
            lastName = binding.surnameRegistrationEditText.text.toString().ifBlank { null },
            middleName = binding.middleNameRegistrationEditText.text.toString().ifBlank { null }
        )
    }

    private fun checkInputLayouts(): Boolean {
        with(binding) {
            val isEmailBlank = valueInputEmailRegistration.isBlank(emailRegistrationEditText)
            val isPasswordBlank =
                valueInputPasswordRegistration.isBlank(passwordRegistrationEditText)
            val isNameBlank = valueInputNameRegistration.isBlank(nameRegistrationEditText)

            val isPasswordTeapot =
                valueInputPasswordRegistration.isTeapot(passwordRegistrationEditText)
            val isEmailValid = valueInputEmailRegistration.isEmail(emailRegistrationEditText)

            return isEmailBlank && isPasswordBlank && isNameBlank && isPasswordTeapot && isEmailValid
        }
    }

    override fun getScreenTitle(): String = getString(R.string.registration_title)
}