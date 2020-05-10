package com.hossam.hasanin.mosalsaltko.repositories

import com.hossam.hasanin.mosalsaltko.datasources.CashDataSource
import com.hossam.hasanin.mosalsaltko.datasources.ScraptingDataSource
import com.hossam.hasanin.mosalsaltko.models.Category
import com.hossam.hasanin.mosalsaltko.models.Post
import io.reactivex.Completable
import io.reactivex.Maybe


class MainRepository(private val dataSource: ScraptingDataSource , private val cashDataSource: CashDataSource){
    fun getPosts(url: String): Maybe<List<Post>>{
        return dataSource.getPosts(url)
    }

    fun getCategories(): Maybe<List<Category>>{
        return dataSource.getCategories()
    }

    fun getCashedPosts(): Maybe<List<Post>>{
        return cashDataSource.getPosts()
    }

    fun addPostsToCash(posts: List<Post>): Completable{
        return cashDataSource.addPosts(posts)
    }

    fun getCashedCats(): Maybe<List<Category>>{
        return cashDataSource.getCats()
    }

    fun addCatsToCash(cats: List<Category>): Completable{
        return cashDataSource.addCats(cats)
    }



}