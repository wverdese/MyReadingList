package me.wverdese.example.myreadinglist.util

import androidx.lifecycle.MutableLiveData


inline fun <T> MutableLiveData<T>.update(block: T.() -> T) {
    value = block(value!!)
}
