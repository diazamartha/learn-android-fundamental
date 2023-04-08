package com.androidstudioproject.mygithubuserapp.userinterface.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudioproject.mygithubuserapp.R
import com.androidstudioproject.mygithubuserapp.data.source.ItemsItem
import com.androidstudioproject.mygithubuserapp.databinding.ActivityFavoriteUserBinding
import com.androidstudioproject.mygithubuserapp.userinterface.detail.FavoriteUserViewModel
import com.androidstudioproject.mygithubuserapp.userinterface.detail.ViewModelFactory

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapter: FavoriteUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(application)
        val favoriteViewModel: FavoriteUserViewModel by viewModels { factory }

        adapter = FavoriteUserAdapter()

        binding.apply {
            rvFavoriteUser.layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            rvFavoriteUser.setHasFixedSize(true)
            rvFavoriteUser.adapter = adapter
        }

        favoriteViewModel.getFavoriteUsers().observe(this) { favoriteList ->
            adapter.updateUserList(favoriteList)
            binding.progressBarFavorite.visibility = View.GONE
        }
    }
}