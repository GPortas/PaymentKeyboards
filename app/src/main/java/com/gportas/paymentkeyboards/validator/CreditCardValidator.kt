package com.gportas.paymentkeyboards.validator

import com.gportas.paymentkeyboards.listener.CreditCardTypeListener

/**
 * Created by guillermo on 23/10/17.
 */

class CreditCardValidator(val listener : CreditCardTypeListener) {
    fun validateNumber(number : String) {
        if(number.equals("555")) {
            listener.onAmericanExpressCardRecognized(number)
        }
        /*listener.onVisaCardRecognized(number)
        listener.onMaestroCardRecognized(number)
        listener.onMasterCardRecognized(number)*/
    }
}