package com.article.core.views

/**
 * If your fragment wants to show custom screen title in the toolbar, implement this
 * interface and override [getScreenTitle] method.
 *
 * NOTE! If screen title can be changed dynamically while fragment is active, you should
 * call [BaseFragment.notifyScreenUpdates] method to re-render activity toolbar.
 */
interface HasCustomTitle {
    fun getScreenTitle(): String?
}