package me.wverdese.example.myreadinglist.util

import androidx.lifecycle.MutableLiveData

class NonNullMutableLiveData<T>(value: T) : MutableLiveData<T>(value) {

  override fun getValue(): T = super.getValue()!!
}
