package com.hiren.livecodingtest.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val name: String,
    val role: String,
    val email: String,
    val username: String,
    val avatar: String,
    val coachId: Int
)
