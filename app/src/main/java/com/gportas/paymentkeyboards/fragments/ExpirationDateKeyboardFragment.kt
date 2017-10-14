package com.gportas.paymentkeyboards.fragments

import com.gportas.paymentkeyboards.listeners.DateChangedListener

/**
 * Created by guillermo on 14/10/17.
 */

class ExpirationDateKeyboardFragment(private val yearsNumber: Int, private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseExpirationDateKeyboardFragment(yearsNumber, primaryColorResId, secondaryColorResId, primaryTextColorResId, secondaryTextColorResId) {

    private var listener : DateChangedListener? = null

    fun setDateChangedListener(listener: DateChangedListener) {
        this.listener = listener
    }

    override fun onDateChanged() {
        if(selectedMonth != null && selectedYear != null) {
            listener!!.onDateChanged(selectedMonth + "/" + selectedYear)
        }
    }
}
