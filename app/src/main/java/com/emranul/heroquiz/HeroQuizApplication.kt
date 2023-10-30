package com.emranul.heroquiz

import android.app.Application
import com.emranul.heroquiz.utils.MySharePreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HeroQuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        /**
         * initialize custom share preference object
         * */
        MySharePreference.init(this)
    }
}