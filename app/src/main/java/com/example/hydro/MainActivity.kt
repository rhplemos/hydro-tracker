package com.example.hydro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hydro.components.CustomProgressIndicator
import com.example.hydro.scenes.main.MainViewModel
import com.example.hydro.ui.theme.HydroTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val progress by viewModel.progress.collectAsState()

            HydroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {
                    Greeting("Android", progress = progress)
                }
            }
        }
    }

    fun setupObservers() {
        viewModel.uiState.waterValue.observe(this@MainActivity){

        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier, progress: Float) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)

        ) {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                SetupWaterIndicator(color = Color.Blue, progress = progress)
                SetupInput()
            }
        }
    }

    @Composable
    fun SetupWaterIndicator(color: Color, progress: Float) {
        CustomProgressIndicator(color = Color.Blue, progress = progress)
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SetupInput() { //isso aqui vai virar o dialog
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            label = { Text("Label") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(onClick = { viewModel.handleWaterValue(text.orEmpty().toFloat()) }, Modifier.width(300.dp)) {
            Text(text = "Clique aqui")
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        HydroTheme {
            Greeting("Teste", progress = 0.5f)
        }
    }
}