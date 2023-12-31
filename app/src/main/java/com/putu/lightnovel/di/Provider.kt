package com.putu.lightnovel.di

import android.app.Application
import androidx.room.Room
import com.putu.lightnovel.database.LightNovelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Provider {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, LightNovelDatabase::class.java, "light.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: LightNovelDatabase) = database.lightNovelDao()

}