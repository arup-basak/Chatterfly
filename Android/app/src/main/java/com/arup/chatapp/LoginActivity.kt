package com.arup.chatapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arup.chatapp.services.NetworkService
import com.arup.chatapp.ui.theme.ChatAppTheme
import com.google.gson.Gson
import io.socket.client.Socket

private var mSocket: Socket? = null
private val LOG_TAG = "MainActivityMessage"
private val gson = Gson()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChatAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Screen(this)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(context: Context) {
    var nameValue by remember {
        mutableStateOf("")
    }

    var passwordValue by remember {
        mutableStateOf("")
    }

    var responseData: LoginData? by remember { mutableStateOf(null) }
    var isLoading by remember { mutableStateOf(true) }
    val networkService = NetworkService()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Login")
                },
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = nameValue,
                onValueChange = {
                    nameValue = it
                },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            )

            TextField(
                value = passwordValue,
                onValueChange = {
                    passwordValue = it
                },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            )

            Button(
                onClick = {
                    context.startActivity(Intent(context, HomeActivity::class.java))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(text = "Sign In")
            }
        }
    }
}

suspend fun signInRequest(context: Context, username: String, password: String): LoginData? {
    try {
        val networkService = NetworkService()
        val map = mapOf(
            "user" to username,
            "password" to password
        )
        val response = networkService.login(context.getString(R.string.server_url), gson.toJson(map))
        return gson.fromJson(response, LoginData::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

//suspend fun signInRequest(
//    activity: Activity,
//    map: Map<String, String>
//) {
//    Log.d(LOG_TAG, map.toString())
//    var responseData: LoginData? = null
//    var isLoading = true
//    val networkService = NetworkService()
//
//    try {
//        val response = networkService
//            .login(activity.getString(R.string.server_url), gson.toJson(map))
//        responseData = gson.fromJson(response, LoginData::class.java)
//        activity.startActivity(Intent(activity, ChatActivity::class.java))
//    }
//    catch (e: Exception) {
//        Log.d(LOG_TAG, e.message.toString())
//        Toast.makeText(activity, "Some Error to Sending SignIn Request", Toast.LENGTH_SHORT).show()
//    }
//}

@Composable
fun NetworkRequestScreen(activity: Activity, url: String, username: String, email: String, password: String) {
    var responseData: LoginData? by remember { mutableStateOf(null) }
    var isLoading by remember { mutableStateOf(true) } // Start with loading state

    val map = mapOf(
        "user" to username,
        "email" to email,
        "password" to password,
    )

    val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    LaunchedEffect(key1 = Unit) {
        try {
            val networkService = NetworkService()
            val response = networkService
                .login(url, gson.toJson(map))
            responseData = gson.fromJson(response, LoginData::class.java)
        } catch (e: Exception) {
            Log.d(LOG_TAG, e.message.toString())
        } finally {
            isLoading = false
        }
    }

    Column {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            if(responseData!!.success) {
                editor.putString(activity.getString(R.string.jwt_token_key), responseData!!.data!!.token)
                editor.apply()
                editor.commit()
            }

            responseData?.data!!.token?.let { Text(text = it) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatAppTheme {
    }
}