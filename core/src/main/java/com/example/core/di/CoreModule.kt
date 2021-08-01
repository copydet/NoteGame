package com.example.core.di

import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.core.BuildConfig
import com.example.core.data.source.GamesRepository
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.GamesDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.RemoteDataSourceDetail
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.repository.IGamesRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<GamesDatabase>().gamesDao()
    }
    single {
        val passPharse: ByteArray = SQLiteDatabase.getBytes("supriyadi".toCharArray())
        val supportFactory = SupportFactory(passPharse)
        Room
            .databaseBuilder(
                androidContext(),
                GamesDatabase::class.java,
                "NoteGames.db"
            )
            .fallbackToDestructiveMigration()
            .openHelperFactory(supportFactory)
            .build()
    }
}

val apiModule = module {
    single {
        val hostName = "api.rawg.io"
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(
                CertificatePinner
                    .Builder()
                    .add(hostName, "sha256/UGwY2lttaRoHnGd1gpeydmov1LzioQpzYTywtNSJkAU=")
                    .add(hostName, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
                    .add(hostName, "sha256/R+V29DqDnO269dFhAAB5jMlZHepWpDGuoejXJjprh7A=")
                    .add(hostName, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
                    .build()
            )
            .build()
    }
    single {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

@ExperimentalPagingApi
val repositoryModule = module {
    factory {
        BuildConfig.KEY
    }
    single { LocalDataSource(get()) }
    single {
        RemoteDataSource(get(), get(), get())
    }
    single {
        RemoteDataSourceDetail(get(), get(), get())
    }

    single<IGamesRepository> {
        GamesRepository(get(), get(), get())
    }
}