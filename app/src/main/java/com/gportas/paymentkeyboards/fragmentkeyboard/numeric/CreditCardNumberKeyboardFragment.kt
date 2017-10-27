package com.gportas.paymentkeyboards.fragmentkeyboard.numeric

import android.widget.TextView
import com.gportas.paymentkeyboards.listener.CreditCardTypeListener
import com.gportas.paymentkeyboards.validator.CreditCardValidator

/**
 * Created by guillermo on 16/10/17.
 */

class CreditCardNumberKeyboardFragment(private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : NumericKeyboardFragment(primaryColorResId, secondaryColorResId, primaryTextColorResId, secondaryTextColorResId) {

    private var cardValidator: CreditCardValidator? = null

    private fun checkIfNumberIsValid() {
        if(cardValidator != null) {
            cardValidator!!.validateCreditCardNumber(number)
        }
    }

    fun setCreditCardTypeListener(listener: CreditCardTypeListener) {
        cardValidator = CreditCardValidator(listener)
    }

    override fun onNumberClicked(numberTextView: TextView) {
        super.onNumberClicked(numberTextView)
        checkIfNumberIsValid()
    }

    override fun onBackSpaceClicked() {
        super.onBackSpaceClicked()
        checkIfNumberIsValid()
    }
}