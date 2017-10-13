package com.gportas.paymentkeyboards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gportas.paymentkeyboards.fragments.ExpirationDateKeyboardFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ExpirationDateKeyboardFragment(20, R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorAccent)
        val fm = supportFragmentManager
        if (fm != null && fragment != null) {
            fm.beginTransaction().replace(R.id.frametest, fragment).commit()
        }

        val firstObserver = object : Observer<String> {

            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onNext(text: String) {
                textView.setText(text)
            }
        }

        fragment.setObserver(firstObserver)
    }
}
