package voiedev

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

fun View.setOnClickCoroutine(owner: LifecycleOwner, listener: suspend (view: View) -> Unit) =
    this.setOnClickListener { owner.lifecycleScope.launch { listener(it) } }