package com.example.crocusoft_android_components.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeView(innerPaddingValues: PaddingValues, onNavigateSecondActivity:(String)->Unit,viewModel: HomeViewModel = hiltViewModel()){

    val state by viewModel.state.collectAsStateWithLifecycle()



    HomeContent(
        innerPaddingValues = innerPaddingValues,
        postIntent = viewModel::onIntent,
        state = state,
        onNavigateSecond = {onNavigateSecondActivity(it)}
    )
}