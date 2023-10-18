package com.arup.chatapp

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import com.arup.chatapp.models.LoginModel
import com.arup.chatapp.models.LoginRequestModel
import com.arup.chatapp.services.NetworkService
import com.arup.chatapp.store.LoginStoreManager
import com.arup.chatapp.ui.theme.ChatAppTheme
import com.google.gson.Gson
import io.socket.client.Socket
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    var emailValue by remember {
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
                value = emailValue,
                onValueChange = {
                    emailValue = it
                },
                label = { Text("Email") },
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
                    signInRequest(
                        context,
                        nameValue,
                        emailValue,
                        passwordValue
                    )

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

private fun signInRequest(
    context: Context,
    username: String,
    email: String,
    password: String
) {
    val retrofitAPI = createRetrofitAPI(context)
    val call = retrofitAPI.loginData(LoginRequestModel(username, email, password))
    val loginStoreManager = LoginStoreManager(context)
    call.enqueue(object : Callback<LoginModel?> {
        override fun onResponse(call: Call<LoginModel?>, response: Response<LoginModel?>) {
            if (response.isSuccessful) {
                if(response.body()!!.success) {
                    val token = response.body()!!.data!!.token
                    val userId = response.body()!!.data!!.userId
                    runBlocking {
                        loginStoreManager.saveLoginDataToDataStore(token, userId)
                    }
                    context.startActivity(Intent(context, HomeActivity::class.java))
                }
                else {
                    Log.d(LOG_TAG, "REQUEST ERROR " + response.body()!!.error)
                }
                Log.d(LOG_TAG, response.body().toString())
            } else {
                Log.d(LOG_TAG, "RESPONSE ERROR " + response.raw().toString())
            }
        }

        override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
            Log.d(LOG_TAG, t.message.toString())
        }
    })
}

private fun createRetrofitAPI(context: Context): RetrofitAPI {
    val retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.server_url))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(RetrofitAPI::class.java)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatAppTheme {
    }
}