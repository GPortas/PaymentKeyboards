package com.gportas.paymentkeyboards.fragmentkeyboard.expirationdate

import com.gportas.paymentkeyboards.listener.DataChangedListener

/**
 * Created by guillermo on 14/10/17.
 */

class ExpirationDateKeyboardFragment(private val yearsNumber: Int, private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseExpirationDateKeyboardFragment(yearsNumber, primaryColorResId, secondaryColorResId, primaryTextColorResId, secondaryTextColorResId) {

    private var listener : DataChangedListener? = null

    fun setDateChangedListener(listener: DataChangedListener) {
        this.listener = listener
    }

    override fun onDateChanged() {
        if(selectedMonth != null && selectedYear != null && listener != null) {
            listener!!.onDataChanged(selectedMonth + "/" + selectedYear)
        }
    }
}
