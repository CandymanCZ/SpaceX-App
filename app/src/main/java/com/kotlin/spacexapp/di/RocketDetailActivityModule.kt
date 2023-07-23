package com.kotlin.spacexapp.di

import com.kotlin.spacexapp.activities.RocketDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RocketDetailActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeRocketDetailActivityInjector(): RocketDetailActivity
}