package com.codewithmohsen.oprestaurantapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.math.ceil
import kotlin.math.floor


fun <T> T.wasted(){}

val <T> T.exhaustive : T
    get() = this

fun List<Any>.toNextPage(offset: Int): Int =
    (ceil(this.count().toDouble() / offset) + 1).toInt()


inline fun <T> MutableLiveData<T>.combineWith(
    liveData: LiveData<T>,
    crossinline block: (T?, T?) -> T
) {
    liveData.observeForever { this.value = block(this.value, it) }
}