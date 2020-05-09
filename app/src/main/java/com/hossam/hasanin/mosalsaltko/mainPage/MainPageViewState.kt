package com.hossam.hasanin.mosalsaltko.mainPage

import com.hossam.hasanin.mosalsaltko.models.Category
import java.lang.Exception

data class MainPageViewState(
    val posts: MutableList<PostWrapper>,
    val categories: MutableList<Category>,
    val error: Exception?,
    val errorCats: Exception?,
    val complete: Boolean,
    val nextPage: Int,
    val loading: Boolean,
    val loadingCategories: Boolean,
    val loadingMore: Boolean,
    val refresh: Boolean,
    val showPosts: Boolean,
    val showGetMore: Boolean
)