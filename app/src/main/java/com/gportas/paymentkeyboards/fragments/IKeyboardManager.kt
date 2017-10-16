package com.gportas.paymentkeyboards.fragments

import android.support.v7.app.AppCompatActivity
import com.gportas.paymentkeyboards.R

/**
 * Created by guillermo on 16/10/17.
 */

interface IKeyboardManager {

    fun openKeyboard(activity: AppCompatActivity, keyboard: ExpirationDateKeyboardFragment, frameLayoutResId: Int) {
        val fm = activity.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.setCustomAnimations(R.anim.keyboard_slide_in_up, R.anim.keyboard_slide_out_up)
        transaction.replace(frameLayoutResId, keyboard).commit()
    }

    fun hideKeyboard(activity: AppCompatActivity, frameLayoutResId: Int){
        val fm = activity.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.remove(fm.findFragmentById(frameLayoutResId)).commit()
    }
}