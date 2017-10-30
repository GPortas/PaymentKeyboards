package com.gportas.paymentkeyboards.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.view.BaseKeyboardFragment
import com.gportas.paymentkeyboards.view.ExpirationDateKeyboardFragment
import com.gportas.paymentkeyboards.managers.IKeyboardManager
import com.gportas.paymentkeyboards.listeners.DataChangedListener
import com.gportas.paymentkeyboards.listeners.CreditCardTypeListener
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.gportas.paymentkeyboards.view.CreditCardNumberKeyboardFragment

/**
 * for testing purpose
 */

class TestActivity : IKeyboardManager, AppCompatActivity() {

    override var isKeyboardOpened: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val fragment1 = ExpirationDateKeyboardFragment(20, R.color.colorPrimary, R.color.colorAccent, R.color.white, R.color.white)
        fragment1.setDateChangedListener(object : DataChangedListener() {
            override fun onDataChanged(data: String) {
                textView.setText(data)
            }
        })

        val fragment2 = CreditCardNumberKeyboardFragment(R.color.colorPrimary, R.color.colorAccent, R.color.white, R.color.material_blue_grey_800)
        fragment2.setCreditCardNumberChangedListener(object : DataChangedListener() {
            override fun onDataChanged(data: String) {
                textView.setText(data)
            }
        })
        fragment2.setCreditCardTypeListener(object : CreditCardTypeListener(){
            override fun onCardRecognized(cardNumber: String, type: String) {
                if(CreditCardTypeListener.TYPE_MASTERCARD == type){
                    Toast.makeText(applicationContext, "Mastercard", Toast.LENGTH_SHORT).show()
                }
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

    override fun openKeyboardWithSlideUpAnimation(activity: AppCompatActivity, keyboard: BaseKeyboardFragment, frameLayoutResId: Int) {
        super.openKeyboardWithSlideUpAnimation(activity, keyboard, frameLayoutResId)
    }

    override fun hideKeyboard(activity: AppCompatActivity, frameLayoutResId: Int) {
        super.hideKeyboard(activity, frameLayoutResId)
    }
}
