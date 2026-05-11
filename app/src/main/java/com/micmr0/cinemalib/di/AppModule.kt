package com.micmr0.cinemalib.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.micmr0.cinemalib.dataStore
import com.micmr0.cinemalib.themoviedb.TMDBService
import com.micmr0.cinemalib.themoviedb.TokenInterceptor
import com.micmr0.cinemalib.themoviedb.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApiService(): TMDBService {
        val tokenManager = TokenManager()
        val tokenInterceptor = TokenInterceptor(tokenManager)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TMDBService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

}