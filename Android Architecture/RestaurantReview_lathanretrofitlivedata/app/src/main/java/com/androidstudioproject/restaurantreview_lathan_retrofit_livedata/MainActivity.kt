package com.androidstudioproject.restaurantreview_lathan_retrofit_livedata

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudioproject.restaurantreview_lathan_retrofit_livedata.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object{
        private const val TAG = "MainActivity"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)  // ?

        mainViewModel.restaurant.observe(this, {restaurant -> // ?
            setRestaurantData(restaurant)
        })

        mainViewModel.listReview.observe(this, { consumerReviews ->  // ?
            setReviewData(consumerReviews)
        })

        mainViewModel.isLoading.observe(this, {     // ?
            showLoading(it)
        })


        binding.rvReview.layoutManager = LinearLayoutManager(this)
        binding.rvReview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager(this).orientation))

        supportActionBar?.hide() // ?
        // findRestaurant()

        binding.btnSend.setOnClickListener { view ->
            // postReview(binding.edReview.text.toString())
            mainViewModel.postReview(binding.edReview.text.toString())  // ?
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }

    private fun setRestaurantData(restaurant: Restaurant) {
        binding.tvTitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description
        Glide.with(this@MainActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(binding.ivPicture)
    }

    private fun setReviewData(consumerReviews: List<CustomerReviewsItem>) {
        val listReview = ArrayList<String>() // By initializing listReview as an empty ArrayList, we can later add elements to it dynamically as needed, without having to worry about the size of the list beforehand.

        val adapter = ReviewAdapter(listReview)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")

        for (review in consumerReviews) {
            listReview.add(  // contoh implementasi dari line 83
                """
                ${review.review}
                - ${review.name}
                """.trimIndent()
            )
        }

    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}