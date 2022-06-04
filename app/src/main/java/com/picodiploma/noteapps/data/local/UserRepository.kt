package com.picodiploma.noteapps.data.local

import androidx.lifecycle.LiveData
import com.picodiploma.noteapps.data.local.entity.UserEntity
import com.picodiploma.noteapps.data.local.room.UserDao

class UserRepository(
    private val userDao: UserDao
) {

    fun getUsers(): LiveData<List<UserEntity>> = userDao.getUsers()

    suspend fun addUser(user: UserEntity) = userDao.addUser(user)

    suspend fun updateUser(user: UserEntity) = userDao.updateUser(user)

    suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)

    suspend fun deleteAllUser() = userDao.deleteAllUser()
}