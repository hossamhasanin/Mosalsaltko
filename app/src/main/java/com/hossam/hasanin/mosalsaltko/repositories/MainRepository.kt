package com.hossam.hasanin.mosalsaltko.repositories

import com.hossam.hasanin.mosalsaltko.datasources.ScraptingDataSource
import com.hossam.hasanin.mosalsaltko.models.Category
import com.hossam.hasanin.mosalsaltko.models.Post
import io.reactivex.rxjava3.core.Maybe

class MainRepository(private val dataSource: ScraptingDataSource){
    fun getPosts(url: String): Maybe<List<Post>>{
        return dataSource.getPosts(url)
    }

    fun getCategories(): Maybe<List<Category>>{
        return dataSource.getCategories()
    }

}