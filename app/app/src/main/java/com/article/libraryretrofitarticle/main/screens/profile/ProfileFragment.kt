package com.article.libraryretrofitarticle.main.screens.profile

import android.os.Bundle
import android.view.View
import com.article.core.utils.collectFlow
import com.article.core.views.*
import com.article.libraryretrofitarticle.R
import com.article.libraryretrofitarticle.databinding.FragmentMainProfileBinding
import com.article.libraryretrofitarticle.main.model.user.entities.PersonalDataEntity

class ProfileFragment : BaseFragment(R.layout.fragment_main_profile), HasCustomTitle,
    HasCustomAction {

    override val viewModel: ProfileViewModel by screenViewModel()
    private lateinit var binding: FragmentMainProfileBinding
    private lateinit var mView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainProfileBinding.bind(view)
        mView = view

        setNotEnabledInputLayouts()
        viewModel.getPersonalData(mView)
        collectFlow(viewModel.personalData) { renderPersonalData(it) }
        binding.editProfileTextView.setOnClickListener { viewModel.launchEditPersonalDataScreen(this@ProfileFragment) }
    }

    private fun renderPersonalData(personalData: PersonalDataEntity) {
        with(binding) {
            emailProfileEditText.setText(if (personalData.email == null) " " else personalData.email)
            nameProfileEditText.setText(if (personalData.firstName == null) " " else personalData.firstName)
            surnameProfileEditText.setText(if (personalData.lastName == null) " " else personalData.lastName)
            middleNameProfileEditText.setText(if (personalData.middleName == null) " " else personalData.middleName)
        }
    }

    private fun setNotEnabledInputLayouts() {
        with(binding) {
            valueInputEmailProfile.isEnabled = false
            valueInputNameProfile.isEnabled = false
            valueInputSurnameProfile.isEnabled = false
            valueInputMiddleNameProfile.isEnabled = false
        }
    }

    override fun getScreenTitle(): String = getString(R.string.profile_title)
    override fun getCustomAction(): CustomAction =
        CustomAction(iconRes = R.drawable.ic_shop, textAction = "Log Out", onCustomAction = { })
}