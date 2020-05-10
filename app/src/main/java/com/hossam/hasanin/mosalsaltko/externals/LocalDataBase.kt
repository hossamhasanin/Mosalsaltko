package com.hossam.hasanin.mosalsaltko.externals

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hossam.hasanin.mosalsaltko.datasources.CashDataSource
import com.hossam.hasanin.mosalsaltko.models.Category
import com.hossam.hasanin.mosalsaltko.models.Post

@Database(entities = [Post::class , Category::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun postsDao() : CashDataSource
}