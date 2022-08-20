package com.shijas.profileapp.data.repository.local


import com.shijas.profileapp.data.local.UserDao
import com.shijas.profileapp.data.mapper.toEntity
import com.shijas.profileapp.data.mapper.toUser
import com.shijas.profileapp.domain.model.User
import com.shijas.profileapp.domain.repository.local.UserRepository
import com.shijas.profileapp.domain.repository.remote.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {
    override suspend fun insertUser(user: User) {
       userDao.addUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toEntity())
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }

    override fun getUsers(): Flow<List<User>> {
       return userDao.getUsers().map { userEntities ->
           userEntities.map { it.toUser() }
       }
    }
}