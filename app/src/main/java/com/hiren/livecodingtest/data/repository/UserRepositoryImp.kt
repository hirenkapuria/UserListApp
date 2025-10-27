package com.hiren.livecodingtest.data.repository

import com.hiren.livecodingtest.data.model.UserModel
import com.hiren.livecodingtest.data.remote.ApiService
import com.hiren.livecodingtest.domin.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImp @Inject constructor(val apiService: ApiService): UserRepository {

    override suspend fun getUsers(): List<UserModel> {
       return withContext(Dispatchers.IO){
            apiService.getUSers()
        }
    }

}