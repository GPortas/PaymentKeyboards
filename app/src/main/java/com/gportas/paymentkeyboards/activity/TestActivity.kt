package com.gportas.paymentkeyboards.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.keyboard.BaseKeyboardFragment
import com.gportas.paymentkeyboards.keyboard.expirationdate.ExpirationDateKeyboardFragment
import com.gportas.paymentkeyboards.keyboard.IKeyboardManager
import com.gportas.paymentkeyboards.keyboard.numeric.NumericKeyboardKeyboardFragment
import com.gportas.paymentkeyboards.listener.DateChangedListener
import kotlinx.android.synthetic.main.activity_main.*

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

        val fragment2 = NumericKeyboardKeyboardFragment(R.color.colorPrimary, R.color.colorAccent, R.color.white, R.color.colorPrimaryDark)

        showButton.setOnClickListener {
            openKeyboard(this, fragment1, frametest.id)
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
