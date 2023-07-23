package com.kotlin.spacexapp.di

import com.kotlin.spacexapp.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    MainActivityModule::class,
    PastLaunchDetailActivityModule::class,
    RocketDetailActivityModule::class
])
interface AppComponent {

    fun inject(application: MyApplication)
}