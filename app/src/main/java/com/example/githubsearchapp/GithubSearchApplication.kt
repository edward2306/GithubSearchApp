package com.example.githubsearchapp

import android.app.Application
import com.example.githubsearchapp.di.githubUserModule
import com.example.githubsearchapp.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GithubSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GithubSearchApplication)
            modules(
                mainModule,
                githubUserModule
            )
        }
    }
}