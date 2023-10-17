package com.arup.chatapp.sampledata

data class LoginModel(
    var success: Boolean,
    var data: TokenData?
)

data class TokenData (
    var userId: String,
    var token: String,
)