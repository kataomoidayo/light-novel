package com.putu.lightnovel.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lightNovel")
data class LightNovelEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val subTitle: String,
    val image: Int,
    val writer: String,
    val ilustrator: String,
    val published: String,
    val label: String,
    val genres: String,
    val volume: String,
    val description: String,
    val plot: String,
    val more: String,
    val storeUrl: String,

    var isFavorite: Boolean = false,

)