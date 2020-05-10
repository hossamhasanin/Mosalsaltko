package com.hossam.hasanin.mosalsaltko.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    var name: String = "",
    var img: String = "",
    val url: String = "",
    val description: String? = ""
): Parcelable{
    companion object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return  oldItem.description.equals(newItem.description)
                    || oldItem.url == newItem.url || oldItem.img == newItem.img         }
    }
}