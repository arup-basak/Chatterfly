package com.arup.chatapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arup.chatapp.models.ChatRequestModel
import com.arup.chatapp.models.ChatResponseModel
import com.arup.chatapp.ui.theme.ChatAppTheme
import com.arup.chatapp.utils.createRetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val LOG_TAG = "HomeActivityMessage"

class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInRequest(this, "arupbasak")
        setContent {
            ChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyDisplay(this)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MyDisplay(context: Context) {
    val retrofitAPI = createRetrofitAPI(context)
    retrofitAPI.getChats(ChatRequestModel("arupbasak"))
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Welcome to Chat App")
                },
            )
        }
    ) {
        Column(
            Modifier.padding(it)
        ) {

            MyCard(
                heading = "This is Heading",
                body = "This is Body"
            )
        }
    }
}

@Composable
fun MyCard(
    heading: String,
    body: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(MaterialTheme.colorScheme.background)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(180.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Icon",
                    modifier = Modifier.padding(16.dp)
                )
            }
            Column {
                Text(
                    text = heading,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Composable
@Preview
fun MyCardPreview() {
    MyCard(
        heading = "This is Heading",
        body = "This is Body"
    )
}

private fun signInRequest(
    context: Context,
    username: String
) {
    val retrofitAPI = createRetrofitAPI(context)
    val call = retrofitAPI.getChats(ChatRequestModel(username))

    call.enqueue(object : Callback<ChatResponseModel?> {
        override fun onResponse(
            call: Call<ChatResponseModel?>,
            response: Response<ChatResponseModel?>
        ) {
            if(response.isSuccessful) {
                if(response.body()!!.response) {
                    Log.d(LOG_TAG, "RESPONSE SUCCESS " + response.body()!!.data)
                }
                else {
                    Log.d(LOG_TAG, "RESPONSE ERROR " + response.body()!!.error)
                }
            } else {
                Log.d(LOG_TAG, "RESPONSE ERROR " + response.raw().toString())
            }
        }

        override fun onFailure(call: Call<ChatResponseModel?>, t: Throwable) {
            Log.d(LOG_TAG, "FETCH ERROR " + t.message)
        }
    })
}