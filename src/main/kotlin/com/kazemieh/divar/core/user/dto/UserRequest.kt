package com.kazemieh.divar.core.user.dto

import com.kazemieh.divar.core.user.entity.User


data class UserRequest(
    val name: String? = null,
    val family: String? = null,
    val email: String? = null,
    val password: String,
    val mobile: String,
    val repeatPassword: String? = null
)

fun UserRequest.toEntity(): User {
    return User(
        name = name ?: "",
        family = family ?: "",
        email = email ?: "",
        password = password,
        mobile = mobile
    )
}