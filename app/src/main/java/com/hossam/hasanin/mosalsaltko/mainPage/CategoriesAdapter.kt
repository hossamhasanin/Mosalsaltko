package com.hossam.hasanin.mosalsaltko.mainPage

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hossam.hasanin.mosalsaltko.R
import com.hossam.hasanin.mosalsaltko.models.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriesAdapter(val action: (String , Int) -> Unit) : ListAdapter<Category, CategoriesAdapter.ViewHolder>(
    Category.diffUtil) {

    var selected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position , getItem(position) , action , selected)
    }

    fun submitSelected(select: Int){
        selected = select
        notifyDataSetChanged()
    }


    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item){
        private val name = item.cat
        fun onBind(position: Int, data: Category, action: (String , Int) -> Unit , selected: Int){

            if (selected == position){
                name.setTextColor(Color.BLUE)
            } else {
                name.setTextColor(Color.BLACK)
            }

            name.text = data.name
            name.setOnClickListener {
                action(data.url , position)
            }
        }
    }

}