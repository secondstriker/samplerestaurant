package com.codewithmohsen.oprestaurantapp.di

import com.codewithmohsen.oprestaurantapp.ui.restaurants.RestaurantsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RestaurantsFragment
}