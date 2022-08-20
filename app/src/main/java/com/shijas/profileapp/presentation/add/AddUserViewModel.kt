
package com.shijas.profileapp.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijas.profileapp.domain.model.User
import com.shijas.profileapp.domain.repository.local.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _saveUser = Channel<AddUserState>()
    val saveUser = _saveUser.receiveAsFlow()

    fun saveUser(user: User) = viewModelScope.launch{
       when{
           user.firstName.isEmpty() ->{
            _saveUser.send(AddUserState.Error("Please enter first Name"))
           }
           user.lastName.isEmpty() ->{
               _saveUser.send(AddUserState.Error("Please enter last Name"))
           }
           user.email.isEmpty() ->{
               _saveUser.send(AddUserState.Error("Please enter email"))
           }
           else->{
               kotlin.runCatching {
                   userRepository.insertUser(user)
               }.onSuccess {  _saveUser.send(AddUserState.Success(SAVE_SUCCESS))
               }.onFailure {
                   _saveUser.send(AddUserState.Error(it.message.toString()))
               }


           }
       }




    }

    companion object {
        const val SAVE_SUCCESS ="User saved"
    }

}