package com.hiren.livecodingtest.domin

import com.hiren.livecodingtest.data.model.UserModel

interface UserRepository {
    suspend fun getUsers(): List<UserModel>
}