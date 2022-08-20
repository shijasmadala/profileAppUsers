package com.shijas.profileapp.app.di

import com.shijas.profileapp.data.repository.local.UserRepositoryImpl
import com.shijas.profileapp.data.repository.remote.WeatherRepositoryImpl
import com.shijas.profileapp.domain.repository.local.UserRepository
import com.shijas.profileapp.domain.repository.remote.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl):WeatherRepository

}