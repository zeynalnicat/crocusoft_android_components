package com.example.crocusoft_android_components.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    innerPaddingValues: PaddingValues,
    postIntent: (HomeContract.Intent) -> Unit,
    state: HomeContract.State,
    onNavigateSecond: (String) -> Unit,
) {

    Scaffold(
        modifier = Modifier.padding(innerPaddingValues)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()

        )
        {

            TextField(
                value = state.name,
                onValueChange = { postIntent(HomeContract.Intent.SetName(it)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                enabled = state.name.isNotEmpty(),
                onClick = { onNavigateSecond(state.name) }
            ) {
                Text(
                    text = "Gönder",
                )
            }

        }
    }

}