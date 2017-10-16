package com.gportas.paymentkeyboards.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.fragments.ExpirationDateKeyboardFragment
import com.gportas.paymentkeyboards.fragments.IKeyboardOpener
import com.gportas.paymentkeyboards.listeners.DateChangedListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * for testing purpose
 */

class MainActivity : IKeyboardOpener, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ExpirationDateKeyboardFragment(20, R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorAccent)
        fragment.setDateChangedListener(object : DateChangedListener() {
            override fun onDateChanged(newDate: String) {
                textView.setText(newDate)
            }
        })

        openKeyBoard(this, fragment, frametest.id)

    }

    override fun openKeyBoard(activity: AppCompatActivity, keyboard: ExpirationDateKeyboardFragment, frameLayoutResId: Int) {
        super.openKeyBoard(activity, keyboard, frameLayoutResId)
    }
}
