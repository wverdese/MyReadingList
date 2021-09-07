package me.wverdese.example.myreadinglist.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe

/**
 * Wrapper for events emitted by a [LiveData].
 *
 * Inspired by https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singlelive
 * event-case-ac2622673150
 */
open class StateEvent<T>(private val content: T) {

  private var hasNotBeenHandled = true

  fun doIfNotHandled(block: (T) -> Unit) {
    if (hasNotBeenHandled) {
      hasNotBeenHandled = false
      block(content)
    }
  }
}

open class SimpleStateEvent : StateEvent<Unit>(Unit)

inline fun <C, T : StateEvent<C>> LiveData<T>.observeEvent(
  owner: LifecycleOwner,
  crossinline onEventNotHandled: (C) -> Unit
) = observe(owner) {
  it.doIfNotHandled { content -> onEventNotHandled(content) }
}
