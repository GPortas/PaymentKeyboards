package com.gportas.paymentkeyboards.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.fragmentkeyboard.BaseKeyboardFragment
import com.gportas.paymentkeyboards.fragmentkeyboard.expirationdate.ExpirationDateKeyboardFragment
import com.gportas.paymentkeyboards.manager.IKeyboardManager
import com.gportas.paymentkeyboards.fragmentkeyboard.numeric.NumericKeyboardFragment
import com.gportas.paymentkeyboards.listener.CreditCardNumberChangedListener
import com.gportas.paymentkeyboards.listener.CreditCardTypeListener
import com.gportas.paymentkeyboards.listener.DateChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast


/**
 * for testing purpose
 */

class TestActivity : IKeyboardManager, AppCompatActivity() {

    override var isKeyboardOpened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val fragment1 = ExpirationDateKeyboardFragment(20, R.color.colorPrimary, R.color.colorAccent, R.color.white, R.color.white)
        fragment1.setDateChangedListener(object : DateChangedListener() {
            override fun onDateChanged(newDate: String) {
                textView.setText(newDate)
            }
        })

        val fragment2 = NumericKeyboardFragment(R.color.colorPrimary, R.color.colorAccent, R.color.white, R.color.colorPrimaryDark)
        fragment2.setCreditCardNumberChangedListener(object : CreditCardNumberChangedListener() {
            override fun onCreditCardNumberChanged(creditCardNumber: String) {
                textView.setText(creditCardNumber)
            }
        })
        fragment2.setCreditCardTypeListener(object : CreditCardTypeListener() {
            override fun onVisaCardRecognized(creditCardNumber: String) {
            }

            override fun onAmericanExpressCardRecognized(creditCardNumber: String) {
                Toast.makeText(applicationContext, "Amex!", Toast.LENGTH_LONG).show()
            }

            override fun onMasterCardRecognized(creditCardNumber: String) {
                Toast.makeText(applicationContext, "Mastercard!", Toast.LENGTH_LONG).show()
            }

            override fun onMaestroCardRecognized(creditCardNumber: String) {
            }
        })
        showButton.setOnClickListener {
            openKeyboard(this, fragment2, frametest.id)
        }
        hideButton.setOnClickListener{
            hideKeyboard(this,frametest.id)
        }
    }

    override fun openKeyboard(activity: AppCompatActivity, keyboard: BaseKeyboardFragment, frameLayoutResId: Int) {
        super.openKeyboard(activity, keyboard, frameLayoutResId)
    }

    override fun hideKeyboard(activity: AppCompatActivity, frameLayoutResId: Int) {
        super.hideKeyboard(activity, frameLayoutResId)
    }
}
