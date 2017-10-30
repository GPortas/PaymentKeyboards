package com.gportas.paymentkeyboards.listeners

/**
 * Created by guillermo on 22/10/17.
 */

abstract class CreditCardTypeListener {
    companion object {
        val TYPE_MASTERCARD = "mastercard"
        val TYPE_AMEX = "amex"
        val TYPE_VISA = "visa"
        val TYPE_MAESTRO = "maestro"
    }

    abstract fun onCardRecognized(cardNumber : String, type : String)
}