package com.hossam.hasanin.mosalsaltko.mainPage

import android.util.Log
import com.hossam.hasanin.mosalsaltko.externals.MAX_POSTS
import com.hossam.hasanin.mosalsaltko.repositories.MainRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class MainPageUseCase(private val repo: MainRepository) {
    fun loadingFirstPage(viewState: MainPageViewState): Observable<MainPageViewState>{
        val url = if (viewState.searchUrl.isNotEmpty()) viewState.searchUrl else "/search?max-results=${MAX_POSTS}"
        Log.v("search" , url)
        return repo.getPosts(url).materialize().map {
            it.value?.let {
                Log.v("search" , it.toString())
                return@map viewState.copy(
                    posts = it.map { PostWrapper(it , PostWrapper.CONTENT) }.toMutableList(),
                    error = null,
                    complete = false,
                    nextPage = 2,
                    showPosts = true,
                    refresh = false,
                    showGetMore = false,
                    loading = false
                )
            }
            it.error?.let {
                return@map viewState.copy(
                    posts = mutableListOf(),
                    error = it as Exception,
                    complete = false,
                    nextPage = 1,
                    showPosts = true,
                    refresh = false,
                    showGetMore = false,
                    loading = false
                )
            }
            return@map viewState.copy(
                posts = mutableListOf(),
                error = null,
                complete = false,
                nextPage = 1,
                showPosts = true,
                refresh = false,
                showGetMore = false,
                loading = false
            )
        }.toObservable().subscribeOn(Schedulers.io())
    }

    fun getCategories(viewState: MainPageViewState): Observable<MainPageViewState>{
        return repo.getCategories().materialize().map {
            it.value?.let {
                return@map viewState.copy(
                    categories = it.toMutableList(),
                    loadingCategories = false
                )
            }
            it.error?.let {
                return@map viewState.copy(
                    categories = mutableListOf(),
                    loadingCategories = false,
                    errorCats = it as Exception
                )
            }
            return@map viewState.copy(
                categories = mutableListOf(),
                loadingCategories = false,
                errorCats = null
            )
        }.toObservable().subscribeOn(Schedulers.io())
    }

    fun getNextPage(viewState: MainPageViewState): Observable<MainPageViewState>{
        val url = if (viewState.searchUrl.isNotEmpty()) viewState.searchUrl + "?max-results=${viewState.nextPage * MAX_POSTS}" else "/search?max-results=${viewState.nextPage * MAX_POSTS}"

        return repo.getPosts(url).materialize().map {
            it.value?.let {
                val l = it.map { PostWrapper(it , PostWrapper.CONTENT) }.toMutableList()
                Log.v("koko" , "got more ${it.size}")
//                l.removeAll(viewState.posts)
                return@map viewState.copy(
                    posts = l,
                    error = null,
                    complete = l.size == viewState.posts.size-1,
                    nextPage = viewState.nextPage + 1,
                    loadingMore = false,
                    showPosts = false,
                    showGetMore = true
                )
            }
            it.error?.let {
                return@map viewState.copy(
                    error = it as Exception,
                    complete = false,
                    loadingMore = false,
                    showPosts = false,
                    showGetMore = true
                )
            }
            return@map viewState.copy(
                error = null,
                complete = false,
                loadingMore = false,
                showPosts = false,
                showGetMore = true
            )
        }.toObservable().subscribeOn(Schedulers.io())
    }

}