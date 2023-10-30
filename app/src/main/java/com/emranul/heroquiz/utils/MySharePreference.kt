package com.emranul.heroquiz.utils

import android.content.Context
import android.content.SharedPreferences

object MySharePreference {
    private var mSharedPref: SharedPreferences? = null
    private const val NAME = "com.emrnaul.heroQuiz"

    fun init(context: Context) {
        if (mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }
    }

    fun getScore(): Int {
        return mSharedPref!!.getInt("score", 0)
    }


    fun saveScore(value: Int) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putInt("score", value)
        prefsEditor.apply()
    }
}