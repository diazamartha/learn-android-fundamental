package com.androidstudioproject.mygithubuserapp.userinterface.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidstudioproject.mygithubuserapp.data.UserRepository
import com.androidstudioproject.mygithubuserapp.data.local.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteUserViewModel(private val userRepo: UserRepository) : ViewModel() {

    fun getFavoriteUsers() = userRepo.getFavoriteUsers()

    fun saveOrDeleteUser(user: UserEntity, favoriteYesOrNo: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (favoriteYesOrNo) {
                userRepo.deleteFavoriteUser(user, false)
            } else {
                userRepo.addFavoriteUser(user, true)
            }
        }

    }
}
