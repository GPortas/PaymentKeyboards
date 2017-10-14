package com.gportas.paymentkeyboards.fragments

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.gportas.paymentkeyboards.R
import kotlinx.android.synthetic.main.fragment_expiration_date_keyboard.*
import java.util.*

/**
 * Created by guillermo on 14/10/17.
 */

abstract class BaseExpirationDateKeyboardFragment(private val yearsNumber: Int, private val primaryColorResId: Int, private val secondaryColorResId: Int, private val primaryTextColorResId: Int, private val secondaryTextColorResId: Int) : BaseFragment() {

    override val fragmentLayout: Int = R.layout.fragment_expiration_date_keyboard

    protected var selectedMonth: String? = null
    protected var selectedYear: String? = null

    private var currentYearTextViewSelected: TextView? = null
    private var currentMonthTextViewSelected: TextView? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        keyboard_root_layout.setBackgroundColor(ContextCompat.getColor(context, primaryColorResId))
        keyboard_month_title.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
        keyboard_year_title.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
        setMonths()
        setYears()
    }

    private fun setMonths() {
        for (i in 0..2) {
            val firstRowMonthTextView = keyboard_first_row_month_layout.getChildAt(i) as TextView
            firstRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
            firstRowMonthTextView.setOnClickListener {
                onMonthSelected(firstRowMonthTextView)
            }
            val secondRowMonthTextView = keyboard_second_row_month_layout.getChildAt(i) as TextView
            secondRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
            secondRowMonthTextView.setOnClickListener {
                onMonthSelected(secondRowMonthTextView)
            }
            val thirdRowMonthTextView = keyboard_third_row_month_layout.getChildAt(i) as TextView
            thirdRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
            thirdRowMonthTextView.setOnClickListener {
                onMonthSelected(thirdRowMonthTextView)
            }
            val fourthRowMonthTextView = keyboard_fourth_row_month_layout.getChildAt(i) as TextView
            fourthRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
            fourthRowMonthTextView.setOnClickListener {
                onMonthSelected(fourthRowMonthTextView)
            }
        }
    }

    private fun setYears() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        for (i in 0..yearsNumber - 1) {
            val yearTextView = createYearTextView((currentYear + i).toString())
            yearTextView.setOnClickListener {
                onYearSelected(yearTextView)
            }
            keyboard_years_scroll_container.addView(yearTextView)
        }
    }

    private fun createYearTextView(year: String): TextView {
        val textView = View.inflate(activity, R.layout.expiration_date_keyboard_year_item, null) as TextView
        textView.text = year
        textView.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
        return textView
    }

    private fun onMonthSelected(monthTextView: TextView) {
        seTextViewAsUnselected(currentMonthTextViewSelected)
        seTextViewAsSelected(monthTextView)
        selectedMonth = monthTextView.text.toString()
        currentMonthTextViewSelected = monthTextView
        onDateChanged()
    }

    private fun onYearSelected(yearTextView: TextView) {
        seTextViewAsUnselected(currentYearTextViewSelected)
        seTextViewAsSelected(yearTextView)
        selectedYear = yearTextView.text.toString()
        currentYearTextViewSelected = yearTextView
        onDateChanged()
    }

    private fun seTextViewAsSelected(textView: TextView?) {
        textView?.setBackgroundColor(ContextCompat.getColor(context, secondaryColorResId))
        textView?.setTextColor(ContextCompat.getColor(context, secondaryTextColorResId))
    }

    private fun seTextViewAsUnselected(textView: TextView?) {
        textView?.setBackgroundColor(ContextCompat.getColor(context, primaryColorResId))
        textView?.setTextColor(ContextCompat.getColor(context, primaryTextColorResId))
    }

    abstract fun onDateChanged()
}