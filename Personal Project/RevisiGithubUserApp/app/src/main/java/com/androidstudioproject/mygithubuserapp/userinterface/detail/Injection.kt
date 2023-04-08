package com.androidstudioproject.mygithubuserapp.userinterface.detail

import android.content.Context
import com.androidstudioproject.mygithubuserapp.data.UserRepository
import com.androidstudioproject.mygithubuserapp.data.local.room.UserRoomDatabase
import com.androidstudioproject.mygithubuserapp.data.remote.api.ApiConfig

object Injection {

    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.apiInstance
        val database = UserRoomDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepository.getInstance(apiService, dao)
    }
}