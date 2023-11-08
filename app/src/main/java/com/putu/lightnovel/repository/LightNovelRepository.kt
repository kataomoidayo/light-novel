package com.putu.lightnovel.repository

import com.putu.lightnovel.database.LightNovelDao
import com.putu.lightnovel.database.LightNovelEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LightNovelRepository @Inject constructor(private val lightNovelDao: LightNovelDao) {

    fun getAllLightNovel() = lightNovelDao.getAllLightNovel()

    fun getLightNovelById(id: Int) = lightNovelDao.getLightNovelById(id)

    fun getAllFavoriteLightNovel() = lightNovelDao.getAllFavoriteLightNovel()

    suspend fun insertAllLightNovel(lightNovelList: List<LightNovelEntity>) = lightNovelDao.insertAllLightNovel(lightNovelList)

    suspend fun updateFavoriteLightNovel(id: Int, isFavorite: Boolean) = lightNovelDao.updateFavoriteLightNovel(id, isFavorite)

    fun searchLightNovel(query: String) = lightNovelDao.searchLightNovel(query)

}