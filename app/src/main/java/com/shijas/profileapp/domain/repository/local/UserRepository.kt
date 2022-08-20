package com.shijas.profileapp.domain.repository.local

import com.shijas.profileapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)

    fun getUsers() : Flow<List<User>>



}
