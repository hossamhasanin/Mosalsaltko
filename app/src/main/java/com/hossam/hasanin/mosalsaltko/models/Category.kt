package com.hossam.hasanin.mosalsaltko.models

import androidx.recyclerview.widget.DiffUtil

data class Category(
    val name: String?,
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