package com.aditya.calender.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CalenderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}