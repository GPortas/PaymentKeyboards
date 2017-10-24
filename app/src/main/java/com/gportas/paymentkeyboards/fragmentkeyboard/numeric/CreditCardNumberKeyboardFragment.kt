package com.gportas.paymentkeyboards.fragmentkeyboard.numeric

/**
 * Created by guillermo on 16/10/17.
 */

class CreditCardNumberKeyboardFragment(private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseNumericKeyboardFragment(primaryColorResId, secondaryColorResId, primaryTextColorResId, secondaryTextColorResId) {

    override fun checkIfNumberIsValid() {
        if(cardValidator != null) {
            cardValidator!!.validateCreditCardNumber(number)
        }
    }
}