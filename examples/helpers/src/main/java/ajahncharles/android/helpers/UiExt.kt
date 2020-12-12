package ajahncharles.android.helpers

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

val Activity.rootView: View
    get() = findViewById(android.R.id.content)

fun Context.toast(message: String) = toastInternal(message, Toast.LENGTH_LONG)
fun Context.toastShort(message: String) = toastInternal(message, Toast.LENGTH_SHORT)

private fun Context.toastInternal(message: String, duration: Int) =
    Toast.makeText(this, message, duration).show()

fun Activity.snack(message: String) = snackInternal(message, Snackbar.LENGTH_LONG)
fun Activity.snackShort(message: String) = snackInternal(message, Snackbar.LENGTH_SHORT)

private fun Activity.snackInternal(message: String, duration: Int) =
    Snackbar.make(rootView, message, duration).show()