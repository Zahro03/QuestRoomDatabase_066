package com.example.meet_9

import android.app.Application
import com.example.meet_9.dependenciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var  containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}