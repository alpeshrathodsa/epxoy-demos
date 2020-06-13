package com.sa.demo.exoxycarouseldemo.base.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sa.demo.exoxycarouseldemo.base.R


object DialogUtils {

    fun alert(context: Context, title: Int, message: Int, buttonTitle: Int, onPositiveButtonClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton(buttonTitle, onPositiveButtonClickListener)
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    fun alert(context: Context, title: String, message: String, buttonTitle: Int, onPositiveButtonClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton(buttonTitle, onPositiveButtonClickListener)
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    fun confirm(
        context: Context,
        title: Int,
        message: Int,
        positiveButtonTitle: Int,
        onPositiveButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonTitle: Int,
        onNegativeButtonClickListener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButtonTitle, onPositiveButtonClickListener)
        builder.setNegativeButton(negativeButtonTitle, onNegativeButtonClickListener)
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    fun confirm(
        context: Context,
        title: Int,
        message: Int,
        positiveButtonTitle: Int,
        onPositiveButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonTitle: Int,
        onNegativeButtonClickListener: DialogInterface.OnClickListener,
        neutralButtonTitle: Int,
        onNeutralButtonClickListener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButtonTitle, onPositiveButtonClickListener)
        builder.setNegativeButton(negativeButtonTitle, onNegativeButtonClickListener)
        builder.setNeutralButton(neutralButtonTitle, onNeutralButtonClickListener)
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    fun confirm(
        context: Context,
        title: Int,
        message: String,
        positiveButtonTitle: Int,
        onPositiveButtonClickListener: DialogInterface.OnClickListener,
        negativeButtonTitle: Int,
        onNegativeButtonClickListener: DialogInterface.OnClickListener,
        neutralButtonTitle: Int,
        onNeutralButtonClickListener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButtonTitle, onPositiveButtonClickListener)
        builder.setNegativeButton(negativeButtonTitle, onNegativeButtonClickListener)
        builder.setNeutralButton(neutralButtonTitle, onNeutralButtonClickListener)
        val alertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    fun customConfirm(
        context: Context, title: Int, message: Int, positiveButtonTitle: Int, onPositiveButtonClickListener: View.OnClickListener,
        negativeButtonTitle: Int, onNegativeButtonClickListener: View.OnClickListener
    ) {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_custom_alert)

        val tvTitle: TextView = dialog.findViewById(R.id.tv_title)
        tvTitle.text = context.getString(title)

        val tvMessage: TextView = dialog.findViewById(R.id.tv_message)
        tvMessage.text = context.getString(message)

        val tvYes: TextView = dialog.findViewById(R.id.tv_yes)
        tvYes.text = context.getString(positiveButtonTitle)
        tvYes.setOnClickListener {
            dialog.dismiss()
            onPositiveButtonClickListener.onClick(it)
        }

        val tvNo: TextView = dialog.findViewById(R.id.tv_no)
        tvNo.text = context.getString(negativeButtonTitle)
        tvNo.setOnClickListener {
            dialog.dismiss()
            onNegativeButtonClickListener.onClick(it)
        }


        dialog.show()
    }


}