package com.androidstudioproject.restaurantreview_lathan_retrofit_livedata

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("detail/{id}")  // annotation @GET untuk mengambil data
    fun getRestaurant(
        @Path("id") id: String  // annotation @Path digunakan untuk mengganti variable {id} pada end
    ): Call<RestaurantResponse>

    @FormUrlEncoded // anotasi @FormUrlEncoded untuk mengirimkan data dengan menggunakan @Field
    @Headers("Authorization: token 12345")
    @POST("review")  // annotation @POST untuk mengirim data
    fun postReview(
        @Field("id") id: String,  // The @Field annotation is used to specify the key-value pairs that will be sent in the request body
        @Field("name") name: String,
        @Field("review") review: String
    ): Call<PostReviewResponse>
}