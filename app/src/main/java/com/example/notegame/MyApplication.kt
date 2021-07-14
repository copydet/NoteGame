package com.example.notegame

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.example.core.di.apiModule
import com.example.core.di.databaseModule
import com.example.core.di.repositoryModule
import com.example.notegame.di.useCaseModule
import com.example.notegame.di.viewModuleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalPagingApi
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    apiModule,
                    repositoryModule,
                    useCaseModule,
                    viewModuleModule
                )
            )
        }
    }
}