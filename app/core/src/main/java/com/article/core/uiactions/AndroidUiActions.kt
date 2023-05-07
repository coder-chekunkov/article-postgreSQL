package com.cdr.core.uiactions

import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.article.core.uiactions.UiActions
import com.google.android.material.snackbar.Snackbar

/**
 * Android implementation of [UiActions].
 */
class AndroidUiActions(private val activity: AppCompatActivity) : UiActions {

    /**
     * Implementation of displaying a simple toast message.
     */
    override fun showToast(message: String) =
        Toast.makeText(activity.applicationContext, message, Toast.LENGTH_SHORT).show()

    /**
     * Implementation of displaying a simple snackbar.
     */
    override fun showSnackbar(view: View, message: String, backgroundColor: Int, mainColor: Int) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(ContextCompat.getColor(activity.applicationContext, backgroundColor))
        snackbar.setTextColor(ContextCompat.getColor(activity.applicationContext, mainColor))
        snackbar.setActionTextColor(ContextCompat.getColor(activity.applicationContext, mainColor))
        snackbar.setAction("OK") { snackbar.dismiss() }
        snackbar.show()
    }

    /**
     * Implementation of displaying a simple alert dialog.
     */
    override fun showAlertDialog(
        icon: Int,
        title: String,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        positiveAction: Runnable
    ) {
        val listener = DialogInterface.OnClickListener { _, clickedButton ->
            when (clickedButton) {
                AlertDialog.BUTTON_POSITIVE -> positiveAction.run()
            }
        }

        val dialog = AlertDialog.Builder(activity)
            .setIcon(ContextCompat.getDrawable(activity.applicationContext, icon))
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText, listener)
            .setNegativeButton(negativeButtonText, listener)

        dialog.show()
    }

    /**
     * Implementation of getting string resource.
     */
    override fun getString(res: Int): String = activity.applicationContext.getString(res)
}