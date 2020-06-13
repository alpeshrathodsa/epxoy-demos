package com.tradeloop.base.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.sa.demo.exoxycarouseldemo.base.R


object SnackBarUtils {
    fun show(view: View, description: Int) {
        show(view, description, Snackbar.LENGTH_LONG)
    }

    fun show(view: View, description: Int, duration: Int) {
        show(view, description, duration, R.string.default_snackbar_action_title,
            View.OnClickListener { })
    }

    fun show(
        view: View,
        description: Int,
        duration: Int,
        actionTitle: Int,
        onActionClickListener: View.OnClickListener
    ) {
        Snackbar.make(view, description, duration)
            .setAction(actionTitle, onActionClickListener)
            .show()
    }

    fun show(view: View, description: String) {
        show(view, description, Snackbar.LENGTH_SHORT)
    }

    fun show(view: View, description: String, duration: Int) {
        show(view, description, duration, R.string.default_snackbar_action_title,
            View.OnClickListener { })
    }

    fun show(
        view: View,
        description: String,
        duration: Int,
        actionTitle: Int,
        onActionClickListener: View.OnClickListener
    ) {
        Snackbar.make(view, description, duration)
            .setAction(actionTitle, onActionClickListener)
            .show()
    }
}