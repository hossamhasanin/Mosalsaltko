package com.hossam.hasanin.mosalsaltko.datasources

import com.hossam.hasanin.mosalsaltko.models.Category
import com.hossam.hasanin.mosalsaltko.models.Post
import io.reactivex.rxjava3.core.Maybe

interface ScraptingDataSource {
    fun getPosts(url: String): Maybe<List<Post>>
    fun getCategories(): Maybe<List<Category>>
}