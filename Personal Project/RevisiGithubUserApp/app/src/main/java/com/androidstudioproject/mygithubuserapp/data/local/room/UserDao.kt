package com.androidstudioproject.mygithubuserapp.data.local.room

import androidx.room.*
import com.androidstudioproject.mygithubuserapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM favorite_user WHERE favorite_yes_or_no = 1")
    fun getFavoriteUsers() : Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToFavorite(user: UserEntity)

    @Delete
    fun deleteFromFavorite(user: UserEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_user WHERE username = :username AND favorite_yes_or_no = 1)")
    suspend fun isUserFavorited(username: String): Boolean
}