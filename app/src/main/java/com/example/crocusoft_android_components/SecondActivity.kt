package com.example.crocusoft_android_components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.crocusoft_android_components.navigation.MainHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                val name = intent.getStringExtra("name")
                MainHost(
                    innerPadding = innerPadding,
                    name = name?:""
                )
            }

        }


    }
}