package com.androidstudioproject.latihanretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter (private val mPost: List<PostResponse>): RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val post: TextView = itemView.findViewById(R.id.tv_post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val PostResponse = mPost[position]

        holder.post.text = "id: ${PostResponse.body}\n" +
                "title: ${PostResponse.title}\n" +
                "body: ${PostResponse.body}"
    }

    override fun getItemCount(): Int = mPost.size

}