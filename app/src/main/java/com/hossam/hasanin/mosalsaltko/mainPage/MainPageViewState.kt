package com.hossam.hasanin.mosalsaltko.mainPage

import com.hossam.hasanin.mosalsaltko.models.Post
import java.lang.Exception

data class MainPageViewState(
    val posts: MutableList<PostWrapper>,
    val error: Exception?,
    val complete: Boolean,
    val nextPage: Int,
    val loading: Boolean,
    val loadingMore: Boolean,
    val refresh: Boolean,
    val showPosts: Boolean,
    val showGetMore: Boolean
)