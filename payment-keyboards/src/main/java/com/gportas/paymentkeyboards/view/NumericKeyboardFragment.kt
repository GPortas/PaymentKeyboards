package com.gportas.paymentkeyboards.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.listeners.DataChangedListener
import kotlinx.android.synthetic.main.fragment_numeric_keyboard.*
import android.content.res.ColorStateList

/**
 * Created by guillermo on 24/10/17.
 */

open class NumericKeyboardFragment(private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseKeyboardFragment() {

    private var dataChangedListener: DataChangedListener? = null

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
        keyboard_backspace.setBackgroundDrawable(makeSelector())
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
        var themeColorStateList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf(android.R.attr.state_enabled), intArrayOf(android.R.attr.state_focused, android.R.attr.state_pressed), intArrayOf(-android.R.attr.state_enabled), intArrayOf()),
                intArrayOf(ContextCompat.getColor(context, secondaryTextColorResId), ContextCompat.getColor(context, primaryTextColorResId), ContextCompat.getColor(context, secondaryTextColorResId), ContextCompat.getColor(context, primaryTextColorResId), ContextCompat.getColor(context, primaryTextColorResId))
        )
        numberTextView.setTextColor(themeColorStateList)
        numberTextView.setBackgroundDrawable(makeSelector())
        numberTextView.setOnClickListener {
            onNumberClicked(numberTextView)
        }
    }

    private fun makeSelector(): StateListDrawable {
        val selector = StateListDrawable()
        selector.setExitFadeDuration(400)
        selector.addState(intArrayOf(android.R.attr.state_pressed), ColorDrawable(ContextCompat.getColor(context, secondaryColorResId)))
        selector.addState(intArrayOf(), ColorDrawable(Color.TRANSPARENT))
        return selector
    }

    fun setCreditCardNumberChangedListener(listener: DataChangedListener) {
        this.dataChangedListener = listener
    }

    fun resetCreditCardNumber(){
        number = ""
        if(dataChangedListener != null) {
            dataChangedListener!!.onDataChanged(number)
        }
    }

    open fun onNumberClicked(numberTextView: TextView) {
        number += numberTextView.text
        if(dataChangedListener != null) {
            dataChangedListener!!.onDataChanged(number)
        }
    }

    open fun onBackSpaceClicked() {
        if(number.equals("")) return
        number = number.substring(0, number.length - 1)
        if(dataChangedListener != null) {
            dataChangedListener!!.onDataChanged(number)
        }
    }
}