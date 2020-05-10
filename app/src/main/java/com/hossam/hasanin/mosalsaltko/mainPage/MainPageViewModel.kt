package com.hossam.hasanin.mosalsaltko.mainPage

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MainPageViewModel(val useCase: MainPageUseCase): ViewModel() {
    val _viewState = BehaviorSubject.create<MainPageViewState>().apply {
        onNext(MainPageViewState(mutableListOf() , mutableListOf() , "" , null , null , false , false , 1 , true , false , false , false , false , false))
    }

    fun viewStateValue(): MainPageViewState = _viewState.value!!

    fun viewState(): Observable<MainPageViewState> = _viewState

    private val compositeDisposable = CompositeDisposable()
    private val _loadingFirstPage = PublishSubject.create<Unit>()
    private val _checkForMore = PublishSubject.create<Unit>()
    private val _loadCats = PublishSubject.create<Unit>()

    init {
        bindUi()
        _loadingFirstPage.onNext(Unit)
        _loadCats.onNext(Unit)
    }

    fun bindUi(){
        val dis = Observable.merge(
            _loadCats(),
            _loadFirstPage(),
            _checkForMore())
            .doOnNext { postViewState(it) }
            .observeOn(AndroidSchedulers.mainThread()).subscribe({}){}
        compositeDisposable.add(dis)
    }

    fun _loadFirstPage(): Observable<MainPageViewState>{
        return _loadingFirstPage.switchMap { useCase.loadingFirstPage(viewStateValue()) }
            .switchMap { useCase.loadOrSavePostsToCash(it) }
    }
    fun _checkForMore() : Observable<MainPageViewState>{
        return _checkForMore.switchMap { useCase.getNextPage(viewStateValue()) }
    }
    fun _loadCats() : Observable<MainPageViewState>{
        return _loadCats.switchMap { useCase.getCategories(viewStateValue()) }
            .switchMap { useCase.loadOrSaveCatsToCash(it) }
    }

    fun checkForMorePosts(){
        if (!viewStateValue().complete && !viewStateValue().showGetMore && !viewStateValue().loadCash) {
            _viewState.onNext(
                viewStateValue().copy(
                    posts = viewStateValue().posts.filter { it.type == PostWrapper.CONTENT }.toMutableList().apply {
                    add(PostWrapper(null , PostWrapper.LOADING))
                    },
                    loadingMore = true
                )
            )
            _checkForMore.onNext(Unit)
        }
    }

    fun showCategory(url: String){
        postViewState(viewStateValue().copy(searchUrl = url , loading = true))
        _loadingFirstPage.onNext(Unit)
    }

    fun loadThePosts(){
        postViewState(viewStateValue().copy(showPosts = true , showGetMore = false))
    }

    fun refreshing(){
        postViewState(viewStateValue().copy(refresh = true))
        _loadingFirstPage.onNext(Unit)
    }



    private fun postViewState(viewState: MainPageViewState){
        _viewState.onNext(viewState)
    }


}