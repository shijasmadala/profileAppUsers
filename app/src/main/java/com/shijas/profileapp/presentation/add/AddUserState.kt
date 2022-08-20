package com.shijas.profileapp.presentation.add

import com.shijas.profileapp.domain.model.User

sealed class AddUserState {
    data class Success(
        val message: String,

        ) : AddUserState()


    data class Error(val message: String) : AddUserState()
    object Loading : AddUserState()
    object Empty : AddUserState()

}