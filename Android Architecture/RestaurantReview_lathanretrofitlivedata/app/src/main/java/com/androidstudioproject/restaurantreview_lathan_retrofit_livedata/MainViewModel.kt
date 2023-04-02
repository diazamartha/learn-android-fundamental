package com.androidstudioproject.restaurantreview_lathan_retrofit_livedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _restaurant = MutableLiveData<Restaurant>() // ?
    val restaurant: LiveData<Restaurant> = _restaurant // ?

    private val _listReview = MutableLiveData<List<CustomerReviewsItem>>() // ?
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview // ?

    private val _isLoading = MutableLiveData<Boolean>() // ?
    val isLoading: LiveData<Boolean> = _isLoading // ?

    companion object {  // ?
        private const val TAG = "MainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    init {
        findRestaurant()  // ?
    }

    private fun findRestaurant(){
        // showLoading(true)
        _isLoading.value = true
        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                // showLoading(false)
                _isLoading.value = false
                val responseBody = response.body()  // Untuk datanya sendiri dapat diambil di response.body()
                if (response.isSuccessful && responseBody != null) {  // kita mengeceknya melalui response.isSuccessful() untuk mengetahui apakah server mengembalikan kode 200 (OK) atau tidak.
                    //setRestaurantData(responseBody.restaurant)
                    _restaurant.value = response.body()?.restaurant
                    //setReviewData(responseBody.restaurant.customerReviews)
                    _listReview.value = response.body()?.restaurant?.customerReviews
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                // showLoading(false)
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun postReview(review: String) {
        // showLoading(true)
        _isLoading.value = true
        val client = ApiConfig.getApiService().postReview(RESTAURANT_ID, "Dicoding", review)
        client.enqueue(object : Callback<PostReviewResponse>{
            override fun onResponse(
                call: Call<PostReviewResponse>,
                response: Response<PostReviewResponse>
            ) {
                // showLoading(false)
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    // setReviewData(responseBody.customerReviews)
                    _listReview.value = response.body()?.customerReviews
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                // showLoading(false)
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}