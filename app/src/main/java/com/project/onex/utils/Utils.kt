package com.project.onex.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// екстеншн функция для Observable
fun <T> Observable<T>.toMainThread() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())