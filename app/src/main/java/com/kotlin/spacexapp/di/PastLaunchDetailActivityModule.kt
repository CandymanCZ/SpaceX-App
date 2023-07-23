package com.kotlin.spacexapp.di

import com.kotlin.spacexapp.activities.PastLaunchDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PastLaunchDetailActivityModule {

    @ContributesAndroidInjector
    abstract fun contributePastLaunchDetailActivityInjector(): PastLaunchDetailActivity
}