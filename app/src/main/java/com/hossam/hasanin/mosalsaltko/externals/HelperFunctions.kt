package com.hossam.hasanin.mosalsaltko.externals

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hossam.hasanin.mosalsaltko.mainPage.PostWrapper

fun MutableList<PostWrapper>.removeLastItem(){
    if (this.isNotEmpty() && this.size > 1){
        this.removeAt(this.lastIndex)
    }
}

fun RecyclerView.onEndReached(block: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                val visibleItems = recyclerView.layoutManager!!.childCount
                val totalCount = recyclerView.layoutManager!!.itemCount
                val pastVisibleItems =
                    (recyclerView.layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()
                if (visibleItems + pastVisibleItems == totalCount ) {
                    block()
                }
            }
        }
    })
}