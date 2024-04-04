package com.example.component.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.component.ui.ui.theme.ComponentTheme

class Button : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { /*TODO*/ },) {
            Text(text = "Button")
        }

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .clickable(enabled = false) {}
        ) {
            Text(text = "Disabled Button")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComponentTheme {
        Greeting("Android")
    }
}