package com.article.core.views

import androidx.annotation.MenuRes

/**
 * If your fragment wants to show several custom actions in toolbar, implement this interface and override [getMultipleCustomAction] method.
 */
interface HasCustomMultipleAction {
    /**
     * @return several custom actions, see [MultipleCustomAction] and [Action] classes for details.
     */
    fun getMultipleCustomAction(): MultipleCustomAction
}

class MultipleCustomAction(@MenuRes val menuRes: Int, val onSeveralCustomActions: List<Action>)
class Action(val id: Int, val action: Runnable)