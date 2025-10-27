package com.hiren.livecodingtest.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiren.livecodingtest.data.model.UserModel
import com.hiren.livecodingtest.domin.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject

sealed class UIState{

    object Loading: UIState()
    data class Success(val users: List<UserModel>): UIState()
    data class Error(val message: String): UIState()
}

@HiltViewModel
class UsersViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _users = MutableStateFlow<List<UserModel>>(emptyList())
    val users: StateFlow<List<UserModel>> = _users

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState


    init {
        getUsers()
    }


    fun getUsers() {
        viewModelScope.launch {
            try {
                _uiState.value = UIState.Loading

                val users = userRepository.getUsers()
                _users.value = users
                _uiState.value = UIState.Success(users)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = UIState.Error(e.message.toString())
            }
        }
    }
}