package com.arup.chatapp

class LoginData {
    var success = false
    var error: String? = null
    internal var data: Data? = null
}

internal class Data {
    var userId: String? = null
    var token: String? = null
    var email: String? = null
}