package com.androidstudioproject.restaurantreview_lathan_retrofit_livedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioproject.restaurantreview_lathan_retrofit_livedata.databinding.ItemReviewBinding

class ReviewAdapter(private val listReview: List<String>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItem.text = listReview[position]
    }

    override fun getItemCount(): Int = listReview.size
}