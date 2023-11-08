package com.putu.lightnovel.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LightNovelEntity::class],
    version = 1,
    exportSchema = false
)

abstract class LightNovelDatabase : RoomDatabase() {
    abstract fun lightNovelDao(): LightNovelDao

}