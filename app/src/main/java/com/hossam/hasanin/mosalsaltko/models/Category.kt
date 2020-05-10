package com.hossam.hasanin.mosalsaltko.models

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Category(
    val name: String?,
    @PrimaryKey
    var url: String
){
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem?.name == newItem?.name
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.url == oldItem.url
            }
        }
    }
}