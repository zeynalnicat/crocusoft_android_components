package com.example.crocusoft_android_components

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.crocusoft_android_components.navigation.MainHost
import com.example.crocusoft_android_components.ui.theme.Primary
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SecondActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize(),
                containerColor = Primary,
                contentColor = Color.White) { innerPadding ->
                val name = intent.getStringExtra("name")
                MainHost(
                    innerPadding = innerPadding,
                    name = name?:""
                )
            }

        }


    }


}