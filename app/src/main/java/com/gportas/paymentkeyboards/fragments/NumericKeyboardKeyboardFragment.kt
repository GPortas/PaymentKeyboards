package com.gportas.paymentkeyboards.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gportas.paymentkeyboards.R
import kotlinx.android.synthetic.main.fragment_numeric_keyboard.*
import android.graphics.drawable.StateListDrawable
import com.gportas.paymentkeyboards.listeners.CreditCardNumberListener


/**
 * Created by guillermo on 16/10/17.
 */

class NumericKeyboardKeyboardFragment(private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseKeyboardFragment() {

    private var listener : CreditCardNumberListener? = null

    override val fragmentLayout: Int = R.layout.fragment_numeric_keyboard

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
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

    private fun onNumberClicked(numberTextView: TextView) {
        onCreditCardNumberChanged()
    }

    private fun onBackSpaceClicked() {
        //todo
    }

    fun setDateChangedListener(listener: CreditCardNumberListener) {
        this.listener = listener
    }

    private fun onCreditCardNumberChanged() {
        //check credit card type and call correct listener method
    }
}