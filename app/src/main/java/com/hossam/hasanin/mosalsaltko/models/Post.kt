package com.hossam.hasanin.mosalsaltko.models

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val url: String = "",
    var name: String = "",
    var img: String = "",
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