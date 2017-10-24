package com.gportas.paymentkeyboards.fragmentkeyboard.numeric

import android.graphics.PorterDuff
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.fragmentkeyboard.BaseKeyboardFragment
import com.gportas.paymentkeyboards.listener.CreditCardNumberChangedListener
import com.gportas.paymentkeyboards.listener.CreditCardTypeListener
import com.gportas.paymentkeyboards.validator.CreditCardValidator
import kotlinx.android.synthetic.main.fragment_numeric_keyboard.*

/**
 * Created by guillermo on 24/10/17.
 */

abstract class BaseNumericKeyboardFragment(private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseKeyboardFragment() {

    protected var cardValidator: CreditCardValidator? = null
    private var creditCardNumberChangedListener : CreditCardNumberChangedListener? = null

    protected var number: String = ""

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

    fun setCreditCardNumberChangedListener(listener: CreditCardNumberChangedListener) {
        this.creditCardNumberChangedListener = listener
    }

    fun resetCreditCardNumber(){
        number = ""
        if(creditCardNumberChangedListener != null) {
            creditCardNumberChangedListener!!.onCreditCardNumberChanged(number)
        }
    }

    private fun onNumberClicked(numberTextView: TextView) {
        number += numberTextView.text
        if(creditCardNumberChangedListener != null) {
            creditCardNumberChangedListener!!.onCreditCardNumberChanged(number)
        }
        checkIfNumberIsValid()
    }

    private fun onBackSpaceClicked() {
        if(number.equals("")) return
        number = number.substring(0, number.length - 1)
        if(creditCardNumberChangedListener != null) {
            creditCardNumberChangedListener!!.onCreditCardNumberChanged(number)
        }
        checkIfNumberIsValid()
    }

    fun setCreditCardTypeListener(listener: CreditCardTypeListener) {
        cardValidator = CreditCardValidator(listener)
    }

    abstract fun checkIfNumberIsValid()
}