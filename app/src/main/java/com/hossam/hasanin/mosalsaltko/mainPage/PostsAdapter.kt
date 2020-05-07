package com.hossam.hasanin.mosalsaltko.mainPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hossam.hasanin.mosalsaltko.R
import com.hossam.hasanin.mosalsaltko.models.Post
import kotlinx.android.synthetic.main.more_button.view.*
import kotlinx.android.synthetic.main.post_item.view.*

class PostsAdapter(private val goToAction: (Post? , HashMap<String , View>? , Boolean) -> Unit):
    ListAdapter<PostWrapper, PostsAdapter.ViewHolder>(PostWrapper.diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            PostWrapper.CONTENT -> {
                PostViewHolder(layoutInflater.inflate(R.layout.post_item , parent , false))
            }
            PostWrapper.LOADING -> {
                LoadingViewHolder(layoutInflater.inflate(R.layout.loading_item , parent , false))
            }
            PostWrapper.GET_MORE -> {
                GetMoreViewHolder(layoutInflater.inflate(R.layout.more_button , parent , false))
            }
            else -> {
                throw IllegalStateException("Not allowed type")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position , getItem(position) , goToAction)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        abstract fun onBind(pos: Int , event: PostWrapper , goToAction: (Post?, HashMap<String , View>? , Boolean) -> Unit)
    }

    class LoadingViewHolder(view: View): ViewHolder(view){
        override fun onBind(pos: Int, post: PostWrapper , goToAction: (Post?, HashMap<String , View>? , Boolean)-> Unit) {

        }
    }

    class GetMoreViewHolder(view: View): ViewHolder(view){
        private val more = view.more
        override fun onBind(pos: Int, postWrapper: PostWrapper , goToAction: (Post?, HashMap<String , View>? , Boolean) -> Unit) {
            more.setOnClickListener {
                goToAction(null , null , true)
            }
        }
    }

    class PostViewHolder(view: View) : ViewHolder(view){
        private val name = view.name
        private val img = view.img
        private val container = view.container

        override fun onBind(pos: Int, postWrapper: PostWrapper , goToAction: (Post?, HashMap<String , View>? , Boolean) -> Unit) {
            name.text = postWrapper.post?.name
            Glide.with(name.context).load(postWrapper.post?.img).into(img)
            container.setOnClickListener {
                val transViews = hashMapOf<String , View>("eventName" to name , "img" to img)
                goToAction(postWrapper.post!! , transViews , false)
            }
        }

    }

}