package com.arup.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arup.chatapp.ui.theme.ChatAppTheme

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun Message(
    text: String,
    time: String,
    position: Boolean // true -> right, false -> left
) {
    val contentAlignment = if (position) {
        Alignment.BottomStart
    } else {
        Alignment.BottomEnd
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(),
        contentAlignment = contentAlignment,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp) // Adjust padding here
                .defaultMinSize(),
        ) {
            Text(
                text = text,
                modifier = Modifier,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = time,
                modifier = Modifier
                    .align(Alignment.End),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun Chat(messages: Array<Array<String>>) {
    LazyColumn {
        items(messages.size) {
            Message(
//                username = messages[it][0],
                text = messages[it][1],
                time = messages[it][2],
                position = messages[it][0] == "user1"
            )
        }
    }
}

@Preview
@Composable
fun MessagePreview() {
    Message(
//        username = "arupbasak",
        text = "This is Message",
        time = "12:26",
        position = true
    )
}

@Preview
@Composable
fun ChatPreview() {
    val messages = arrayOf(
        arrayOf("user1", "Hello", "10:00 AM"),
        arrayOf("user2", "Hi there!", "10:05 AM"),
        arrayOf("user1", "How are you?", "10:10 AM"),
        arrayOf("user2", "I'm good, thanks!", "10:15 AM"),
        arrayOf("user1", "That's great!", "10:20 AM"),
        arrayOf("user2", "Yeah, it is!", "10:25 AM"),
        arrayOf("user1", "What have you been up to?", "10:30 AM"),
        arrayOf("user2", "Just working on some projects.", "10:35 AM"),
        arrayOf("user1", "That sounds interesting.", "10:40 AM"),
        arrayOf("user2", "Yeah, I'm quite busy these days.", "10:45 AM"),
        arrayOf("user1", "I understand. We should catch up soon.", "10:50 AM"),
        arrayOf("user2", "Absolutely! Let's plan something.", "10:55 AM"),
    )

    Chat(messages = messages)
}