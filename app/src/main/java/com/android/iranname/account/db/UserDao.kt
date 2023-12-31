package com.android.iranname.account.db

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface UserDao {

    /*
     * this function is going to use in log in and sign up sections
     * which will save our new user data
     */
    @Insert
    suspend fun newUser(user: User)

    @Update
    /*
     * this function will update our user edited information
     */
    suspend fun editUser(user: User)

    /*
     * this function will delete user details after logout or delete account
     */
    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getFirstUser(): User?

}