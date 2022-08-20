package com.shijas.profileapp.app.di

import android.app.Application
import androidx.room.Room
import com.shijas.profileapp.data.local.UserDao
import com.shijas.profileapp.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(application: Application) = Room.databaseBuilder(
        application,
        UserDatabase::class.java,
        "user_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao
    }
}