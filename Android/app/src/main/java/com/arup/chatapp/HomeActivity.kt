package com.arup.chatapp

import android.content.Context
import android.os.Bundle
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arup.chatapp.models.ChatItemModel
import com.arup.chatapp.models.ChatRequestModel
import com.arup.chatapp.models.ChatResponseModel
import com.arup.chatapp.ui.theme.ChatAppTheme
import com.arup.chatapp.utils.createRetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch

private const val LOG_TAG = "HomeActivityMessage"

class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyDisplay(context = this)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MyDisplay(context: Context) {
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
            ListScreen(context = context)
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

@Composable
private fun ListScreen(context: Context) {
    var data by remember { mutableStateOf(listOf<ChatItemModel>()) }

    LaunchedEffect(Unit) {
        val newData = createRetrofitAPI(context)
        data = newData.getChats(ChatRequestModel("arupbasak")).data
    }

    LazyColumn {
        items(data) {
            MyCard(heading = it.chatId, body = it.users.toString())
        }
    }
}