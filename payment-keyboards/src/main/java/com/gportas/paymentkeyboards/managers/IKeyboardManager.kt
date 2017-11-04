package com.gportas.paymentkeyboards.managers

import android.support.v7.app.AppCompatActivity
import com.gportas.paymentkeyboards.R
import com.gportas.paymentkeyboards.view.BaseKeyboardFragment

/**
 * Created by guillermo on 16/10/17.
 */

interface IKeyboardManager {

    var isKeyboardOpened: Boolean

    fun openKeyboardWithSlideUpAnimation(activity: AppCompatActivity, keyboard: BaseKeyboardFragment, frameLayoutResId: Int) {
        isKeyboardOpened = true
        val fm = activity.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.setCustomAnimations(R.anim.keyboard_slide_in_up, R.anim.keyboard_slide_out_up)
        transaction.replace(frameLayoutResId, keyboard).commit()
    }

    fun openKeyboard(activity: AppCompatActivity, keyboard: BaseKeyboardFragment, frameLayoutResId: Int) {
        isKeyboardOpened = true
        val fm = activity.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(frameLayoutResId, keyboard).commit()
    }

    fun hideKeyboard(activity: AppCompatActivity, frameLayoutResId: Int){
        if(!isKeyboardOpened) return
        val fm = activity.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.remove(fm.findFragmentById(frameLayoutResId)).commit()
        isKeyboardOpened = false
    }
}