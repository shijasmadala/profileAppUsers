package com.shijas.profileapp.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijas.profileapp.domain.model.User
import com.shijas.profileapp.domain.repository.local.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
   private val userRepository: UserRepository
) : ViewModel() {

    val users = userRepository.getUsers()

    fun userDelete(user: User) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }



}