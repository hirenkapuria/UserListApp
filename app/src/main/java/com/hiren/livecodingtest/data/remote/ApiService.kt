package com.hiren.livecodingtest.data.remote

import com.hiren.livecodingtest.data.model.UserModel
import retrofit2.http.GET

interface ApiService {


    @GET("/users")
    suspend fun getUSers(): List<UserModel>

}



