package com.codewithmohsen.oprestaurantapp.di

import android.app.Application
import android.content.Context
import com.codewithmohsen.oprestaurantapp.api.MenuService
import com.codewithmohsen.oprestaurantapp.api.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMenuService(): MenuService {
        return Retrofit.Builder()
            .baseUrl("https://cdn.opeqe.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
            .create(MenuService::class.java)
    }
    @Singleton
    @Provides
    fun provideContext(application: Application): Context{
        return application.applicationContext
    }
}