package com.hossam.hasanin.mosalsaltko.mainPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hossam.hasanin.mosalsaltko.R
import com.hossam.hasanin.mosalsaltko.models.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriesAdapter(val action: (String) -> Unit) : ListAdapter<Category, CategoriesAdapter.ViewHolder>(
    Category.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.more_button,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position) , action)
    }


    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item){
        private val name = item.cat
        fun onBind(data: Category, action: (String) -> Unit){
            name.text = data.name
            name.setOnClickListener {
                action(data.url)
            }
        }
    }

}