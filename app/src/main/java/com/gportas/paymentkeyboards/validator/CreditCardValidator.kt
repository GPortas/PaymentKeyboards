package com.gportas.paymentkeyboards.validator

import com.gportas.paymentkeyboards.listener.CreditCardTypeListener

/**
 * Created by guillermo on 23/10/17.
 */

class CreditCardValidator(val listener : CreditCardTypeListener) {
    private val CREDIT_CARD_MASTERCARD_NUMBER_REGEX = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$"
    private val CREDIT_CARD_AMEX_NUMBER_REGEX = "^3[47][0-9]{13}$"
    private val CREDIT_CARD_VISA_NUMBER_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$"
    private val CREDIT_CARD_MAESTRO_NUMBER_REGEX = "^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$"

    private val CREDIT_CARD_MASTERCARD_CVV_REGEX = "^\\d{3}$"
    private val CREDIT_CARD_AMEX_CVV_REGEX = "^\\d{4}$"
    private val CREDIT_CARD_VISA_CVV_REGEX = "^\\d{3}$"
    private val CREDIT_CARD_MAESTRO_CVV_REGEX = "^\\d{3}$"


    fun validateCreditCardNumber(number : String) {
        when {
            number.matches(Regex(CREDIT_CARD_MASTERCARD_NUMBER_REGEX)) -> listener.onMasterCardRecognized(number)
            number.matches(Regex(CREDIT_CARD_AMEX_NUMBER_REGEX)) -> listener.onAmericanExpressCardRecognized(number)
            number.matches(Regex(CREDIT_CARD_VISA_NUMBER_REGEX)) -> listener.onVisaCardRecognized(number)
            number.matches(Regex(CREDIT_CARD_MAESTRO_NUMBER_REGEX)) -> listener.onMaestroCardRecognized(number)
        }
    }

    fun validateCVVNumber(number : String) {
        when {
            number.matches(Regex(CREDIT_CARD_MASTERCARD_CVV_REGEX)) -> listener.onMasterCardRecognized(number)
            number.matches(Regex(CREDIT_CARD_AMEX_CVV_REGEX)) -> listener.onAmericanExpressCardRecognized(number)
            number.matches(Regex(CREDIT_CARD_VISA_CVV_REGEX)) -> listener.onVisaCardRecognized(number)
            number.matches(Regex(CREDIT_CARD_MAESTRO_CVV_REGEX)) -> listener.onMaestroCardRecognized(number)
        }
    }
}