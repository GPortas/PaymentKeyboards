package com.gportas.paymentkeyboards.listener

/**
 * Created by guillermo on 23/10/17.
 */

abstract class CreditCardNumberChangedListener {
    internal abstract fun onCreditCardNumberChanged(creditCardNumber : String)
}