package com.article.core.navigator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.article.core.views.*
import com.google.android.material.appbar.MaterialToolbar

class NavigatorExecutor(
    private val activity: AppCompatActivity,
    private var navController: NavController,
    private val fragmentContainer: Int,
    private val toolbar: MaterialToolbar?,
    private val defaultTitle: String,
    private val topLevelDestinations: List<Int>,
) : Navigator {

    /**
     * Launching a new screen using a Navigation Controller.
     */
    override fun launchByNavController(navController: NavController, direction: NavDirections) {
        navController.navigate(direction)
    }

    /**
     * Launching a new screen using a Top Navigation Controller.
     */
    override fun launchByTopNavController(fragment: Fragment, direction: NavDirections) {
        findTopNavController(fragment).navigate(direction)
    }

    /**
     * PopBackStack using a Navigation Controller.
     */
    override fun popBackStackByNavController(navController: NavController) {
        navController.popBackStack()
    }

    /**
     * PopBackStack using a Top Navigation Controller.
     */
    override fun popBackStackByTopNavController(fragment: Fragment) {
        findTopNavController(fragment).popBackStack()
    }

    /**
     * Launching a new activity using Intent.
     */
    override fun launchNewActivity(
        nextActivity: AppCompatActivity,
        addToBackStack: Boolean,
        startDestination: Int?
    ) {
        val classOfTheNextActivity = nextActivity::class.java

        val intent = Intent(activity, classOfTheNextActivity)
        intent.putExtra(START_DESTINATION_TAG, startDestination)

        activity.startActivity(intent)
        activity.finish()
    }

    /**
     * A method that duplicates the Activity lifecycle-method.
     */
    @SuppressLint("UseSupportActionBar")
    fun onCreate() {
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)
        if (toolbar != null) activity.setSupportActionBar(toolbar)
    }

    /**
     * A method that duplicates the Activity lifecycle-method.
     */
    fun onDestroy() {
        activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallbacks)
    }

    /**
     * A method that duplicates the Activity method.
     */
    fun onSupportNavigateUp() = navController.navigateUp()

    /**
     * A method of "activating" of Navigation Controller.
     */
    private fun onNavControllerActivated(navController: NavController?) {
        this.navController.removeOnDestinationChangedListener(destinationListener)
        navController?.addOnDestinationChangedListener(destinationListener)
        if (navController != null) {
            this.navController = navController
        }
    }

    /**
     * Destination changer listener.
     */
    private val destinationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
        }

    /**
     * Checking if destination is start on graph.
     */
    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestinations + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    /**
     * Find NavController from high levels.
     */
    private fun findTopNavController(fragment: Fragment): NavController {
        val topLevelHost = fragment.requireActivity().supportFragmentManager
            .findFragmentById(fragmentContainer) as NavHostFragment?
        return topLevelHost?.navController ?: fragment.findNavController()
    }

    /**
     * Method to update items on activity (e.g. toolbar).
     */
    fun notifyScreenUpdates() {
        var f =
            activity.supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.first()
        while (f?.childFragmentManager?.primaryNavigationFragment?.childFragmentManager?.fragments?.first() != null) {
            f =
                f.childFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.first()
        }

        // if toolbar is exist on activity
        if (toolbar != null) {
            // fragment has custom screen title -> display it
            if (f is HasCustomTitle) toolbar.title = f.getScreenTitle()
            else toolbar.title = defaultTitle

            // fragment has custom action -> display it
            if (f is HasCustomAction) createCustomToolbarAction(f.getCustomAction())
            else toolbar.menu.clear()

            // fragment has several custom actions -> display it
            if (f is HasCustomMultipleAction) createCustomMultipleToolbarActions(f.getMultipleCustomAction())
            else activity.invalidateOptionsMenu()
        }

        onNavControllerActivated(f?.findNavController())
    }

    /**
     * Method of updating UI with custom action. If custom implements [HasCustomAction].
     */
    private fun createCustomToolbarAction(action: CustomAction) {
        toolbar!!.menu.clear()

        val iconDrawable =
            DrawableCompat.wrap(ContextCompat.getDrawable(activity, action.iconRes)!!)
        iconDrawable.setTint(Color.WHITE)

        val menuItem = toolbar.menu.add(action.textAction)
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.icon = iconDrawable
        menuItem.setOnMenuItemClickListener {
            action.onCustomAction.run()
            return@setOnMenuItemClickListener true
        }
    }

    /**
     * Method of updating UI with several custom actions. If custom implements [HasCustomMultipleAction].
     */
    private fun createCustomMultipleToolbarActions(actions: MultipleCustomAction) {
        activity.invalidateOptionsMenu()
        toolbar!!.menu.clear()

        toolbar.inflateMenu(actions.menuRes)
        toolbar.menu.forEach { item ->
            item.setOnMenuItemClickListener {
                val itemId = it.itemId
                val actionId = actions.onSeveralCustomActions.indexOfFirst { a -> a.id == itemId }

                if (actionId == -1) return@setOnMenuItemClickListener false
                actions.onSeveralCustomActions[actionId].action.run()
                return@setOnMenuItemClickListener true
            }
        }
    }

    /**
     * Controlling object lifecycle callbacks of fragment.
     */
    private val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?
        ) {
            notifyScreenUpdates()
        }
    }

    companion object {
        const val START_DESTINATION_TAG = "start_destination"
    }
}