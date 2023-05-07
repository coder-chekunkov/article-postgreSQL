package com.article.core.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.article.core.views.BaseFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Collect items from the specified [Flow] only when fragment is at least in STARTED state.
 */
fun <T> BaseFragment.collectFlow(flow: Flow<T>, onCollect: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        // this coroutine is cancelled in onDestroyView
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            // this coroutine is launched every time when onStart is called;
            // collecting is cancelled in onStop
            flow.collect {
                onCollect(it)
            }
        }
    }
}