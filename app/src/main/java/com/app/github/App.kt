package com.app.github

import android.app.Application
import android.content.Context
import com.app.github.di.gitHubInfo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(listOf(gitHubInfo))
        }
    }
}