package com.androidstudioproject.mygithubuserapp.userinterface.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidstudioproject.mygithubuserapp.data.UserRepository
import com.androidstudioproject.mygithubuserapp.userinterface.main_activity.MainViewModel

class ViewModelFactory private constructor(private val userRepo: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteUserViewModel::class.java)){
            return FavoriteUserViewModel(userRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}