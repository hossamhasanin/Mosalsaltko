package com.hossam.hasanin.mosalsaltko.mainPage

import androidx.recyclerview.widget.DiffUtil
import com.hossam.hasanin.mosalsaltko.models.Post


data class PostWrapper (
    val post: Post?,
    val type: Int
){
    companion object {
        val CONTENT = 0
        val LOADING = 1
        val GET_MORE = 2

        val diffUtil = object : DiffUtil.ItemCallback<PostWrapper>() {
            override fun areItemsTheSame(oldItem: PostWrapper, newItem: PostWrapper): Boolean {
                return oldItem.post?.name == newItem.post?.name
            }

            override fun areContentsTheSame(oldItem: PostWrapper, newItem: PostWrapper): Boolean {
                return Post.areContentsTheSame(oldItem.post ?: Post() , newItem.post ?: Post())
            }
        }
    }
}