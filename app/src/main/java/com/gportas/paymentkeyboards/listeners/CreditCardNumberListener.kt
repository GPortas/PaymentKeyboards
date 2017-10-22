package com.gportas.paymentkeyboards.listeners

/**
 * Created by guillermo on 22/10/17.
 */

abstract class CreditCardNumberListener {
    abstract fun onVisaCardRecognized(creditCardNumber : String)
    abstract fun onAmericanExpressRecognized(creditCardNumber : String)
    abstract fun onMasterCardRecognized(creditCardNumber : String)
    abstract fun onMaestroCardRecognized(creditCardNumber : String)
}