package com.article.core.views

import androidx.fragment.app.Fragment
import com.article.core.utils.internet.IsInternetConnection

/**
 * Base class for all fragments
 */
abstract class BaseFragment(layout: Int) : Fragment(layout) {
    /**
     * View-model that manages this fragment
     */
    abstract val viewModel: BaseViewModel

    /**
     * Call this method when activity controls (e.g. toolbar) should be re-rendered
     */
    fun notifyScreenUpdates() = (requireActivity() as FragmentHolder).notifyScreenUpdates()

    /**
     * Call this method to get status of internet connection.
     */
    fun checkInternetConnection(): Boolean =
        (requireActivity() as IsInternetConnection).checkInternetConnection()
}