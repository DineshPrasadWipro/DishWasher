package com.jlp.dishwasher

import android.app.Application
import com.jlp.dishwasher.injection.repositoryModule
import com.jlp.dishwasher.injection.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DishWasherApplication : Application() {

    private val appModules = listOf(
        repositoryModule,
        vmModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DishWasherApplication)
            modules(appModules)
        }
    }

}