package com.article.core.views

import com.article.core.ActivityScopeViewModel

/**
 * Implement this interface in the activity.
 */
interface FragmentHolder {
    /**
     * Called when activity views should be re-drawn.
     */
    fun notifyScreenUpdates()

    /**
     * Get the current implementations of dependencies from activity VM scope.
     */
    fun getActivityScopeViewModel(): ActivityScopeViewModel

    /**
     * Get the list of application dependencies.
     */
    fun getApplicationDependencies(): List<Any>
}