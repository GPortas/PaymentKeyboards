package com.gportas.paymentkeyboards.fragmentkeyboard.expirationdate

import com.gportas.paymentkeyboards.listener.DateChangedListener

/**
 * Created by guillermo on 14/10/17.
 */

class ExpirationDateKeyboardFragment(private val yearsNumber: Int, private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseExpirationDateKeyboardKeyboardFragment(yearsNumber, primaryColorResId, secondaryColorResId, primaryTextColorResId, secondaryTextColorResId) {

    private var listener : DateChangedListener? = null

    fun setDateChangedListener(listener: DateChangedListener) {
        this.listener = listener
    }

    override fun onDateChanged() {
        if(selectedMonth != null && selectedYear != null && listener != null) {
            listener!!.onDateChanged(selectedMonth + "/" + selectedYear)
        }
    }
}