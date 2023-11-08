package com.putu.lightnovel.di

import com.putu.lightnovel.database.LightNovelDao
import com.putu.lightnovel.repository.LightNovelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object Injection {

    @Provides
    @ViewModelScoped
    fun provideRepository(lightNovelDao: LightNovelDao) = LightNovelRepository(lightNovelDao)

}