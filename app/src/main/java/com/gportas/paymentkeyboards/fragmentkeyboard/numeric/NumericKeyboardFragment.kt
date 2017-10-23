package com.gportas.paymentkeyboards.fragmentkeyboard.numeric

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gportas.paymentkeyboards.R
import kotlinx.android.synthetic.main.fragment_numeric_keyboard.*
import android.graphics.drawable.StateListDrawable
import com.gportas.paymentkeyboards.fragmentkeyboard.BaseKeyboardFragment
import com.gportas.paymentkeyboards.listener.CreditCardNumberChangedListener
import com.gportas.paymentkeyboards.listener.CreditCardTypeListener
import com.gportas.paymentkeyboards.validator.CreditCardValidator


/**
 * Created by guillermo on 16/10/17.
 */

class NumericKeyboardFragment(private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseKeyboardFragment() {

    private var creditCardNumberChangedListener : CreditCardNumberChangedListener? = null
    private var cardValidator: CreditCardValidator? = null

    private var creditCardNumber : String = ""

    override val fragmentLayout: Int = R.layout.fragment_numeric_keyboard

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        keyboard_root_layout.setBackgroundColor(ContextCompat.getColor(context, primaryColorResId))
        initializeBackSpaceButton()
        initializeNumbers()
    }

    private fun initializeBackSpaceButton() {
        keyboard_backspace_icon.setColorFilter(ContextCompat.getColor(context, primaryTextColorResId), PorterDuff.Mode.SRC_IN)
        keyboard_backspace.setOnClickListener {
            onBackSpaceClicked()
        }
    }

    private fun initializeNumbers() {
        for (column in 0..2) {
            initializeNumberButton(column, keyboard_first_row_layout)
            initializeNumberButton(column, keyboard_second_row_layout)
            initializeNumberButton(column, keyboard_third_row_layout)
            if(column < 1) {
                initializeNumberButton(column, keyboard_fourth_row_layout)
            }
        }
    }

    private fun initializeNumberButton(column: Int, viewGroup: ViewGroup) {
        val numberTextView = viewGroup.getChildAt(column) as TextView
        numberTextView.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
        //numberTextView.setBackgroundDrawable(makeSelector(Color.RED))
        numberTextView.setOnClickListener {
            onNumberClicked(numberTextView)
        }
    }

    private fun makeSelector(color: Int): StateListDrawable {
        val selector = StateListDrawable()
        //todo
        return selector
    }

    fun setCreditCardTypeListener(listener: CreditCardTypeListener) {
        cardValidator = CreditCardValidator(listener)
    }

    fun setCreditCardNumberChangedListener(listener: CreditCardNumberChangedListener) {
        this.creditCardNumberChangedListener = listener
    }

    fun resetCreditCardNumber(){
        creditCardNumber = ""
        if(creditCardNumberChangedListener != null) {
            creditCardNumberChangedListener!!.onCreditCardNumberChanged(creditCardNumber)
        }
    }

    private fun onNumberClicked(numberTextView: TextView) {
        creditCardNumber += numberTextView.text
        if(creditCardNumberChangedListener != null) {
            creditCardNumberChangedListener!!.onCreditCardNumberChanged(creditCardNumber)
        }
        checkIfCreditCardNumberIsValid()
    }

    private fun onBackSpaceClicked() {
        if(creditCardNumber.equals("")) return
        creditCardNumber = creditCardNumber.substring(0, creditCardNumber.length - 1)
        if(creditCardNumberChangedListener != null) {
            creditCardNumberChangedListener!!.onCreditCardNumberChanged(creditCardNumber)
        }
        checkIfCreditCardNumberIsValid()
    }

    private fun checkIfCreditCardNumberIsValid() {
        if(cardValidator != null) {
            cardValidator!!.validateNumber(creditCardNumber)
        }
    }
}