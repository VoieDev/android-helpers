package ajahncharles.android.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass

inline fun <reified T : Activity> Context.start(
    cls: KClass<T>, noinline init: (Intent.() -> Unit)? = null
) {
    Intent(this, cls.java).apply {
        init?.invoke(this)
        startActivity(this)
    }
}

inline fun <reified T : Activity> Context.menuStart(
    cls: KClass<T>, noinline init: (Intent.() -> Unit)? = null
): Boolean {
    start(cls, init)
    return true
}