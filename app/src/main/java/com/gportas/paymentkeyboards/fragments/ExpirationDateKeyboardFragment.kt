package com.gportas.paymentkeyboards.fragments

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.gportas.paymentkeyboards.R
import kotlinx.android.synthetic.main.fragment_expiration_date_keyboard.*
import rx.Observable
import rx.Observer
import java.util.*

/**
 * Created by guillermo on 13/10/17.
 */

class ExpirationDateKeyboardFragment(private val yearsNumber: Int, private val primaryColor : Int, private val secondaryColor : Int, private val primaryTextColor : Int, private val secondaryTextColor : Int) : BaseFragment() {

    override val fragmentLayout: Int = R.layout.fragment_expiration_date_keyboard

    private var selectedMonth : String? = null
    private var selectedYear : String? = null

    private var currentYearTextViewSelected: TextView? = null
    private var currentMonthTextViewSelected: TextView? = null

    private var observers = arrayListOf<Observer<String>>()

    private val keyboardObservable = Observable.create(
            Observable.OnSubscribe<String> { sub ->
                sub.onNext(selectedMonth + "/" + selectedYear)
                sub.onCompleted()
            }
    )

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        keyboard_root_layout.setBackgroundColor(ContextCompat.getColor(context, primaryColor))
        keyboard_month_title.setTextColor(ContextCompat.getColor(context, primaryTextColor))
        keyboard_year_title.setTextColor(ContextCompat.getColor(context, primaryTextColor))
        setMonths()
        setYears()
    }

    private fun setMonths() {
        for (i in 0..2) {
            val firstRowMonthTextView = keyboard_first_row_month_layout.getChildAt(i) as TextView
            firstRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColor))
            firstRowMonthTextView.setOnClickListener {
                onMonthSelected(firstRowMonthTextView)
            }
            val secondRowMonthTextView = keyboard_second_row_month_layout.getChildAt(i) as TextView
            secondRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColor))
            secondRowMonthTextView.setOnClickListener {
                onMonthSelected(secondRowMonthTextView)
            }
            val thirdRowMonthTextView = keyboard_third_row_month_layout.getChildAt(i) as TextView
            thirdRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColor))
            thirdRowMonthTextView.setOnClickListener {
                onMonthSelected(thirdRowMonthTextView)
            }
            val fourthRowMonthTextView = keyboard_fourth_row_month_layout.getChildAt(i) as TextView
            fourthRowMonthTextView.setTextColor(ContextCompat.getColor(context, primaryTextColor))
            fourthRowMonthTextView.setOnClickListener {
                onMonthSelected(fourthRowMonthTextView)
            }
        }
    }

    private fun setYears() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        for (i in 0..yearsNumber - 1) {
            val yearTextView = createYearTextView((currentYear + i).toString())
            yearTextView.setOnClickListener{
                onYearSelected(yearTextView)
            }
            keyboard_years_scroll_container.addView(yearTextView)
        }
    }

    private fun createYearTextView(year: String): TextView {
        val textView = View.inflate(activity, R.layout.expiration_date_keyboard_year_item, null) as TextView
        textView.text = year
        textView.setTextColor(ContextCompat.getColor(context, primaryTextColor))
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
        textView?.setBackgroundColor(ContextCompat.getColor(context, secondaryColor))
        textView?.setTextColor(ContextCompat.getColor(context, secondaryTextColor))
    }

    private fun seTextViewAsUnselected(textView: TextView?) {
        textView?.setBackgroundColor(ContextCompat.getColor(context, primaryColor))
        textView?.setTextColor(ContextCompat.getColor(context, primaryTextColor))
    }

    private fun onDateChanged() {
        if(selectedMonth != null && selectedYear != null) {
            for (observer in observers) {
                keyboardObservable.subscribe(observer)
            }
        }
    }

    fun setObserver(observer : Observer<String>) {
        observers.add(observer)
    }
}