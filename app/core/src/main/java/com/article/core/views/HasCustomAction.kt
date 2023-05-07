package com.article.core.views

import androidx.annotation.DrawableRes

/**
 * If your fragment wants to show custom action in the toolbar, implement this interface and override [getCustomAction] method.
 */
interface HasCustomAction {
    /**
     * @return a custom action specification, see [CustomAction] class for details.
     */
    fun getCustomAction(): CustomAction
}

class CustomAction(
    @DrawableRes val iconRes: Int,
    val textAction: String,
    val onCustomAction: Runnable
)