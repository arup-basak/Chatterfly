package com.arup.chatapp.models

data class LoginModel(
    var success: Boolean,
    var data: TokenData?,
    var error: String?
)

data class TokenData (
    var userId: String,
    var token: String,
)