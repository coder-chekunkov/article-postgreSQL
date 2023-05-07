package com.article.core.navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.article.core.utils.ResourceActions

/**
 * Mediator that holds nav-actions in the queue if real navigator is not active.
 */
class IntermediateNavigator : Navigator {

    private val targetNavigator = ResourceActions<Navigator>()

    /**
     * Implementation of launching a new screen using a Navigation Controller.
     */
    override fun launchByNavController(navController: NavController, direction: NavDirections) = targetNavigator {
        it.launchByNavController(navController, direction)
    }

    /**
     * Implementation of launching a new direction using a Top Navigation Controller.
     */
    override fun launchByTopNavController(fragment: Fragment, direction: NavDirections) = targetNavigator {
        it.launchByTopNavController(fragment, direction)
    }

    /**
     * Implementation of PopBackStack using a Navigation Controller.
     */
    override fun popBackStackByNavController(navController: NavController) = targetNavigator {
        it.popBackStackByNavController(navController)
    }

    /**
     * Implementation of PopBackStack using a Top Navigation Controller.
     */
    override fun popBackStackByTopNavController(fragment: Fragment) = targetNavigator {
        it.popBackStackByTopNavController(fragment)
    }

    /**
     * Implementation of launching a new activity.
     */
    override fun launchNewActivity(
        nextActivity: AppCompatActivity,
        addToBackStack: Boolean,
        startDestination: Int?
    ) = targetNavigator {
        it.launchNewActivity(nextActivity, addToBackStack, startDestination)
    }

    /**
     * Set navigator in target.
     */
    fun setTarget(navigator: Navigator?) {
        targetNavigator.resource = navigator
    }

    /**
     * Clean all navigation.
     */
    fun cleared() {
        targetNavigator.clear()
    }
}