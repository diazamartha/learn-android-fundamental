package com.androidstudioproject.mygithubuserapp.userinterface.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidstudioproject.mygithubuserapp.userinterface.main_activity.MainViewModel

class SettingViewModelFactory (private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        }
        throw IllegalArgumentException("ViewModel tidak dapat dikenali" + modelClass.name)
    }
}