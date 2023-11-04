package com.arup.chatapp.models

data class ChatResponseModel(
    var response: Boolean,
    var error: String,
    var data: List<ChatItemModel>
)

data class ChatItemModel (
    var chatId: String,
    var users: List<String>
)