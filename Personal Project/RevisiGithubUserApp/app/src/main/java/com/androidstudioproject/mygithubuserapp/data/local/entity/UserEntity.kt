package com.androidstudioproject.mygithubuserapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_user")
@Parcelize
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    val avatar_url: String,

    @ColumnInfo(name = "favorite_yes_or_no")
    var favoritesYesOrNo: Boolean
) : Parcelable
