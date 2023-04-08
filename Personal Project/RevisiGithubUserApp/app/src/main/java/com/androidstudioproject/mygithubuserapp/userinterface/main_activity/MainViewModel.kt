package com.androidstudioproject.mygithubuserapp.userinterface.main_activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.androidstudioproject.mygithubuserapp.data.UserRepository
import com.androidstudioproject.mygithubuserapp.data.remote.api.ApiConfig
import com.androidstudioproject.mygithubuserapp.data.source.GithubResponse
import com.androidstudioproject.mygithubuserapp.data.remote.response.User
import com.androidstudioproject.mygithubuserapp.userinterface.setting.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val pref: SettingPreferences):ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        ApiConfig.apiInstance
            .getSerchUsers(query)
            .enqueue(object : Callback<GithubResponse>{
                override fun onResponse(
                    call: Call<GithubResponse>,
                    response: Response<GithubResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getSearchUsers() : LiveData<ArrayList<User>> {
        return listUsers
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}