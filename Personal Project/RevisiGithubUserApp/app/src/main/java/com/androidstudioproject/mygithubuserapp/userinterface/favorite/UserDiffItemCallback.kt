package com.androidstudioproject.mygithubuserapp.userinterface.favorite

import androidx.recyclerview.widget.DiffUtil
import com.androidstudioproject.mygithubuserapp.data.local.entity.UserEntity

class UserDiffItemCallback(private val oldList: List<UserEntity>, private val newList: List<UserEntity>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList == newList

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition].login
        val latest = newList[newItemPosition].login
        return old == latest
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

}