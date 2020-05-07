package com.hossam.hasanin.mosalsaltko.datasources

import com.hossam.hasanin.mosalsaltko.models.Post
import io.reactivex.rxjava3.core.Maybe

interface ScraptingDataSource {
    fun getPosts(url: String): Maybe<List<Post>>
    //fun isThereMorePosts(page: String): Maybe<List<Post>>
}