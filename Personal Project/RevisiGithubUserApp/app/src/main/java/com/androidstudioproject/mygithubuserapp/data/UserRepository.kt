package com.androidstudioproject.mygithubuserapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.androidstudioproject.mygithubuserapp.data.local.entity.UserEntity
import com.androidstudioproject.mygithubuserapp.data.local.room.UserDao
import com.androidstudioproject.mygithubuserapp.data.remote.api.ApiService

class UserRepository(
    private val apiService: ApiService,
    private val userDao: UserDao) {

    fun getFavoriteUsers(): LiveData<List<UserEntity>> {
        return userDao.getFavoriteUsers().asLiveData()
    }

    fun addFavoriteUser(user: UserEntity, favoriteCondition: Boolean) {
        user.favoritesYesOrNo = favoriteCondition
        userDao.insertToFavorite(user)
    }

    fun deleteFavoriteUser(user: UserEntity, favoriteCondition: Boolean) {
        user.favoritesYesOrNo = favoriteCondition
        userDao.deleteFromFavorite(user)
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao,
            //appExecutor: AppExecutor
        ): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(apiService, userDao)
            }.also { INSTANCE = it }
    }
}