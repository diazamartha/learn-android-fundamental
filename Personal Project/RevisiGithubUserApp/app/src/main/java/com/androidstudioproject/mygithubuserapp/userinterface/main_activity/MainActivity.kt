package com.androidstudioproject.mygithubuserapp.userinterface.main_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudioproject.mygithubuserapp.R
import com.androidstudioproject.mygithubuserapp.data.remote.response.User
import com.androidstudioproject.mygithubuserapp.databinding.ActivityMainBinding
import com.androidstudioproject.mygithubuserapp.userinterface.detail.DetailUserActivity
import com.androidstudioproject.mygithubuserapp.userinterface.favorite.FavoriteUserActivity
import com.androidstudioproject.mygithubuserapp.userinterface.setting.SettingActivity
import com.androidstudioproject.mygithubuserapp.userinterface.setting.SettingPreferences
import com.androidstudioproject.mygithubuserapp.userinterface.setting.SettingViewModelFactory

class MainActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })
        //viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(MainViewModel::class.java)

        binding.apply {
            rvUserGithub.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUserGithub.setHasFixedSize(true)
            rvUserGithub.adapter = adapter

            btnSearch.setOnClickListener{
                searchUser()
            }

            editQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUsers().observe(this, {
            if (it!=null) {
                adapter.setList(it)
                showLoading(false)
            }
        })

        viewModel.getThemeSettings().observe(this) { activateDarkMode: Boolean ->
            if (activateDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun searchUser() {
        binding.apply {
            val query = editQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favorite_button -> {
                Intent(this, FavoriteUserActivity::class.java).also{
                    startActivity(it)
                }
            }
            R.id.setting_button -> {
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}