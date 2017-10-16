package com.gportas.paymentkeyboards.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.fragments.ExpirationDateKeyboardFragment
import com.gportas.paymentkeyboards.fragments.IKeyboardManager
import com.gportas.paymentkeyboards.listeners.DateChangedListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * for testing purpose
 */

class MainActivity : IKeyboardManager, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ExpirationDateKeyboardFragment(20, R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorAccent)
        fragment.setDateChangedListener(object : DateChangedListener() {
            override fun onDateChanged(newDate: String) {
                textView.setText(newDate)
            }
        })
        showButton.setOnClickListener {
            openKeyboard(this, fragment, frametest.id)
        }
        hideButton.setOnClickListener{
            hideKeyboard(this,frametest.id)
        }
    }

    override fun openKeyboard(activity: AppCompatActivity, keyboard: ExpirationDateKeyboardFragment, frameLayoutResId: Int) {
        super.openKeyboard(activity, keyboard, frameLayoutResId)
    }

    override fun hideKeyboard(activity: AppCompatActivity, frameLayoutResId: Int) {
        super.hideKeyboard(activity, frameLayoutResId)
    }
}
