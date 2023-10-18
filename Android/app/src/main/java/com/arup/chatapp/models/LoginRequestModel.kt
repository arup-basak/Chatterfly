package com.arup.chatapp.models

data class LoginRequestModel(
    var user: String,
    var email: String,
    var password: String
)
