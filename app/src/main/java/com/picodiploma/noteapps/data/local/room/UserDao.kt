package com.picodiploma.noteapps.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.picodiploma.noteapps.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUsers(): LiveData<List<UserEntity>>

}