package com.example.quizapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.annotation.StringRes
import kotlin.reflect.KClass

fun <T : Activity> KClass<T>.start(activity: Activity, finish: Boolean = false) {
    Intent(activity, this.java).apply {
        activity.startActivity(this)
    }

    if (finish)
        activity.finish()
}

fun @receiver:StringRes Int.alertDialog(activity: Activity) {
    AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert)
        .setTitle("Note")
        .setMessage(this@alertDialog)
        .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        .setIcon(android.R.drawable.ic_dialog_alert).show()
}