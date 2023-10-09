package com.arup.chatapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import io.socket.client.Socket
import java.net.URISyntaxException


class ChatService: Service() {
    private var mSocket: Socket? = null

    init {
        try {
//            mSocket = IO.socket(getString(R.string.server_url))
            Log.d("ChatService", "Connected")
        }
        catch (e: URISyntaxException) {
            Log.d("ChatService", e.message.toString())
        }
    }

    override fun onCreate() {
        super.onCreate()
        mSocket!!.connect()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start your Socket.IO client and run it in the background
        mSocket!!.connect()
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}