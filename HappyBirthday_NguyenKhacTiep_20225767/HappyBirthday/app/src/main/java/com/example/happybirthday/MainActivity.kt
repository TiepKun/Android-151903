package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
        Surface(
modifier = Modifier.fillMaxSize(),
color = MaterialTheme.colorScheme.background
) {
    GreetingText(
        message = "Happy Birthday, Sam!",
        from = "From Emma.",
        modifier = Modifier.padding(44.dp)
    )
}
}
}
}
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween, // Space between items
        modifier = modifier.fillMaxSize() // Fill the whole available space
    ) {
        Text(
            text = message,
            fontSize = 34.sp,
            lineHeight = 34.sp,
            textAlign = TextAlign.Left
        )
        Text(
            text = from,
            fontSize = 34.sp,
            modifier = Modifier
                .padding(34.dp) // Padding to position it correctly
        )
    }
}