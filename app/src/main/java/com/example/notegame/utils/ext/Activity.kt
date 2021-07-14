package com.example.notegame.utils.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T)-> Unit){
    liveData.removeObservers(this)
    liveData.observe(this, Observer(body))
}
