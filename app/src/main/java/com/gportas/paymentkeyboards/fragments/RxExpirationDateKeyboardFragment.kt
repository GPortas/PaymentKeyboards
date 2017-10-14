package com.gportas.paymentkeyboards.fragments

import rx.Observable
import rx.Observer

/**
 * Created by guillermo on 13/10/17.
 */

class RxExpirationDateKeyboardFragment(private val yearsNumber: Int, private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseExpirationDateKeyboardFragment(yearsNumber, primaryColorResId, secondaryColorResId, primaryTextColorResId, secondaryTextColorResId) {

    private var observers = arrayListOf<Observer<String>>()

    private val keyboardObservable = Observable.create(
            Observable.OnSubscribe<String> { sub ->
                sub.onNext(selectedMonth + "/" + selectedYear)
                sub.onCompleted()
            }
    )

    fun addObserver(observer: Observer<String>) {
        observers.add(observer)
    }

    override fun onDateChanged() {
        if(selectedMonth != null && selectedYear != null) {
            for (observer in observers) {
                keyboardObservable.subscribe(observer)
            }
        }
    }
}
