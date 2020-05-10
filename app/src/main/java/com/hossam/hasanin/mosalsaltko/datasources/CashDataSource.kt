package com.hossam.hasanin.mosalsaltko.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hossam.hasanin.mosalsaltko.models.Category
import com.hossam.hasanin.mosalsaltko.models.Post
import io.reactivex.Completable
import io.reactivex.Maybe


@Dao
interface CashDataSource {

    @Query("SELECT * FROM posts")
    fun getPosts(): Maybe<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE , entity = Post::class)
    fun addPosts(posts: List<Post>) : Completable

    @Query("SELECT * FROM cats")
    fun getCats(): Maybe<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE , entity = Category::class)
    fun addCats(cats: List<Category>) : Completable

}