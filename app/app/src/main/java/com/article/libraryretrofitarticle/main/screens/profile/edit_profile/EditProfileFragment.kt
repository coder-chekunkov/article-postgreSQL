package com.article.libraryretrofitarticle.main.screens.profile.edit_profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.article.core.views.BaseFragment
import com.article.core.views.HasCustomTitle
import com.article.core.views.screenViewModel
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentMainProfileEditBinding
import com.article.libraryretrofitarticle.utils.isBlank

class EditProfileFragment : BaseFragment(R.layout.fragment_main_profile_edit), HasCustomTitle {

    override val viewModel: EditProfileViewModel by screenViewModel()
    private lateinit var binding: FragmentMainProfileEditBinding
    private val args by navArgs<EditProfileFragmentArgs>()
    private lateinit var mView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainProfileEditBinding.bind(view)
        mView = view

        with(binding) {
            val personalData = args.personalData

            valueInputEmailEditProfile.isEnabled = false
            emailEditProfileEditText.setText(if (personalData.email == null) " " else personalData.email)
            nameEditProfileEditText.setText(personalData.firstName)
            surnameEditProfileEditText.setText(personalData.lastName)
            middleNameProfileEditEditText.setText(personalData.middleName)

            saveDataButton.setOnClickListener { saveNewData() }
        }
    }

    private fun saveNewData() {
        if (checkInputLayouts()) viewModel.saveNewData(view = mView,
            firstName = binding.nameEditProfileEditText.text.toString(),
            lastName = binding.surnameEditProfileEditText.text.toString().ifBlank { null },
            middleName = binding.middleNameProfileEditEditText.text.toString().ifBlank { null })
    }

    private fun checkInputLayouts(): Boolean {
        with(binding) {
            val isNameBlank = valueInputNameEditProfile.isBlank(nameEditProfileEditText)

            return isNameBlank
        }
    }

    override fun getScreenTitle(): String = getString(R.string.edit_profile_title)
}