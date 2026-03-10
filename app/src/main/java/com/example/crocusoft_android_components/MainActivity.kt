package com.example.crocusoft_android_components

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.crocusoft_android_components.presentation.home.HomeView
import com.example.crocusoft_android_components.ui.theme.Crocusoft_android_componentsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeView(
                        innerPaddingValues = innerPadding,
                        onNavigateSecondActivity = {
                            val intent = Intent(this, SecondActivity::class.java)
                            intent.putExtra("name", it)
                            startActivity(intent)
                        }
                    )

                }
        }
    }
}

