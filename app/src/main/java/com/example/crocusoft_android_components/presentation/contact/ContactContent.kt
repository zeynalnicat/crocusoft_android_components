package com.example.crocusoft_android_components.presentation.contact

import android.annotation.SuppressLint
import android.view.RoundedCorner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactContent(
    innerPaddingValues: PaddingValues,
    state: ContactContract.State,
    postIntent: (ContactContract.Intent) -> Unit,
) {

    LaunchedEffect(Unit) {
        postIntent(ContactContract.Intent.FetchContact)
    }

    Scaffold(
        modifier = Modifier.padding(innerPaddingValues)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = state.contacts.size,
                key = {state.contacts[it].id}
            ){
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.Magenta,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp).fillMaxWidth()
                    ){
                        Text(
                            text = state.contacts[it].name,
                            color = Color.White
                        )

                        Text(
                            text = state.contacts[it].number,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

}