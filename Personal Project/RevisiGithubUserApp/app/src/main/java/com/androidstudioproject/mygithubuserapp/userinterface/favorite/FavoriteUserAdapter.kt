package com.androidstudioproject.mygithubuserapp.userinterface.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioproject.mygithubuserapp.data.local.entity.UserEntity
import com.androidstudioproject.mygithubuserapp.databinding.ItemUserGithubBinding
import com.androidstudioproject.mygithubuserapp.userinterface.detail.DetailUserActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class FavoriteUserAdapter : RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>(){

    private var userList = emptyList<UserEntity>()

    fun updateUserList(newList: List<UserEntity>) {
        val diff = DiffUtil.calculateDiff(UserDiffItemCallback(userList, newList))
        this.userList = newList

        diff.dispatchUpdatesTo(this)
    }

    class ViewHolder(private val binding: ItemUserGithubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.apply {
                tvUsername.text = user.login
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    //.transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivUser)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

}