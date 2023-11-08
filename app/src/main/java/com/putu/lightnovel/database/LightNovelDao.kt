package com.putu.lightnovel.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LightNovelDao {

    @Query("SELECT * FROM lightNovel")
    fun getAllLightNovel(): Flow<List<LightNovelEntity>>

    @Query("SELECT * FROM lightNovel WHERE id = :id")
    fun getLightNovelById(id: Int): Flow<LightNovelEntity>

    @Query("SELECT * FROM lightNovel WHERE isFavorite = 1")
    fun getAllFavoriteLightNovel(): Flow<List<LightNovelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLightNovel(lightNovelList: List<LightNovelEntity>)

    @Query("UPDATE lightNovel SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteLightNovel(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM lightNovel WHERE title LIKE '%' || :query || '%' OR subTitle LIKE '%' || :query || '%'")
    fun searchLightNovel(query: String): Flow<List<LightNovelEntity>>

}