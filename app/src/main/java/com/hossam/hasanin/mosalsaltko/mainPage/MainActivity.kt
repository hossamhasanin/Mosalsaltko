package com.hossam.hasanin.mosalsaltko.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hossam.hasanin.mosalsaltko.R
import com.hossam.hasanin.mosalsaltko.externals.onEndReached
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainPageViewModel by viewModel()
    private lateinit var disposable: Disposable

    //private var selectedCat = 0

    private val postsAdapter = PostsAdapter(goToAction = {post, transElments, getMore ->
        if (getMore){
            removeTheButton()
            viewModel.loadThePosts()
        }
    })

    private val categoriesAdapter = CategoriesAdapter(action = { url , select ->
        viewModel.showCategory(url)
        setS(select)
    })

    fun removeTheButton(){
        val l = postsAdapter.currentList.toMutableList()
        l.removeAt(l.lastIndex)
        postsAdapter.submitList(l)
    }

    fun setS(s: Int){
        categoriesAdapter.submitSelected(s)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var l = mutableListOf<PostWrapper>()
        re_posts.layoutManager = LinearLayoutManager(this)
        categories.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)

        //categoriesAdapter.submitSelected(selectedCat)

        disposable = viewModel._viewState.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                // loading state
                if (it.loading) {
                    loading.visibility = View.VISIBLE
                } else {
                    loading.visibility = View.GONE
                }

                if (it.loadingCategories){
                    loading_cats.visibility = View.VISIBLE
                } else {
                    loading_cats.visibility = View.GONE
                }

                //refresh casr
                if (!it.refresh) swipe_refresh.isRefreshing = false

//                // the error case
//                if (it.error != null) {
//                    tv_error_mess.visibility = View.VISIBLE
//                    tv_error_mess.text = it.error.localizedMessage
//                }else{
//                    tv_error_mess.visibility = View.GONE
//                }
//
//                if (it.errorCats != null){
//                    cat_error_mess.visibility = View.VISIBLE
//                    cat_error_mess.text = it.errorCats.localizedMessage
//                } else {
//                    cat_error_mess.visibility = View.GONE
//                }

                if (it.posts.isNotEmpty() && it.showPosts){
                    Log.v("koko" , "${it.posts.size}")
                    l = it.posts.toMutableList()
                    postsAdapter.submitList(l)
                    loading.visibility = View.GONE
                }

                if (it.categories.isNotEmpty()){
                    Log.v("koko" , "cats ${it.categories}")
                    categoriesAdapter.submitList(it.categories)
                    loading_cats.visibility = View.GONE
                }

                if (!it.loadingMore){
                    if (l.size > 0){
                        if (l[l.lastIndex].type == PostWrapper.LOADING){
                            Log.v("koko" , "remove loading item")
                            l[l.lastIndex] = PostWrapper(null , PostWrapper.GET_MORE)
                            postsAdapter.submitList(l)
                            postsAdapter.notifyItemChanged(l.lastIndex)
//                            CoroutineScope(Dispatchers.Main).launch {
//                                l.removeAt(l.lastIndex)
//                                l.add(PostWrapper(null , PostWrapper.GET_MORE))
//                                postsAdapter.submitList(l)
//                            }
                        }
                    }
                }
                if (it.complete){
                    if (l.size > 0){
                        if (l[l.lastIndex].type != PostWrapper.CONTENT){
                            Log.v("koko" , "loading item")
                            l.removeAt(l.lastIndex)
                        }
                        postsAdapter.submitList(l)
                        postsAdapter.notifyItemRemoved(l.lastIndex)
                    }
                }
                Log.v("koko" , it.complete.toString())
            }

        re_posts.adapter = postsAdapter
        categories.adapter = categoriesAdapter

        swipe_refresh.setOnRefreshListener {
            viewModel.refreshing()
        }

        re_posts.onEndReached {
            viewModel.checkForMorePosts()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}