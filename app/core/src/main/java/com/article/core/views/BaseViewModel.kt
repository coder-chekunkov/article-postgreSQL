package com.article.core.views

import androidx.lifecycle.ViewModel

/**
 * Base class for all view-models.
 */
open class BaseViewModel : ViewModel() {
    /**
     * Override this method in child classes if you want to listen for results
     * from other screens
     */
    open fun onResult(result: Any) {}
}