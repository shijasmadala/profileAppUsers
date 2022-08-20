package com.shijas.profileapp.data.mapper

import com.shijas.profileapp.data.local.UserEntity
import com.shijas.profileapp.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}

fun User.toEntity() : UserEntity {
    return UserEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}