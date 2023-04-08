package com.androidstudioproject.mygithubuserapp.userinterface.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.androidstudioproject.mygithubuserapp.R
import com.androidstudioproject.mygithubuserapp.data.local.entity.UserEntity
import com.androidstudioproject.mygithubuserapp.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private var avatar_url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(application)
        val favoriteViewModel: FavoriteUserViewModel by viewModels { factory }

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                showLoading(false)
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
            showLoading(true)
        }

        favoriteViewModel.getFavoriteUsers().observe(this) { favoriteList ->
            val favoriteYesOrNo = favoriteList.any {
                it.login == username
            }
            setIconFavorite(favoriteYesOrNo)

            binding.fbFavorite.setOnClickListener {
                val favoriteCondition = username?.let { UserEntity(it, avatar_url, false) }

                try {
                    if (favoriteCondition != null) favoriteViewModel.saveOrDeleteUser(favoriteCondition, favoriteList.any {
                        it.login == username
                    })
                } catch (e: Exception) {
                    Toast.makeText(
                        this, e.toString(), Toast.LENGTH_SHORT
                    ).show()
                }

                if (favoriteYesOrNo) {
                    Toast.makeText(
                        this, "Berhasil menghapus user ini dari favorit", Toast.LENGTH_SHORT
                    ).show()
                    setIconFavorite(favoriteYesOrNo)
                } else {
                    Toast.makeText(
                        this, "Berhasil menambahkan user ini ke favorit", Toast.LENGTH_SHORT
                    ).show()
                    setIconFavorite(favoriteYesOrNo)
                }
            }
        }

    }

    private fun setIconFavorite(favoriteYesOrNo: Boolean) {
        binding.fbFavorite.apply {
            if (favoriteYesOrNo) {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailUserActivity,
                        R.drawable.ic_favorite_red
                    )
                )
            } else {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailUserActivity,
                        R.drawable.ic_favorite_border_red
                    )
                )
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}