package com.example.crocusoft_android_components.presentation.second

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.crocusoft_android_components.navigation.AppRoutes


@Composable
fun SecondView(innerPaddingValues: PaddingValues,name:String,navController: NavController) {


    SecondContent(
        paddingValues = innerPaddingValues,
        name = name,
        onNavigateMusic = {
            navController.navigate(AppRoutes.Music)
        },
        onNavigateContact = {
            navController.navigate(AppRoutes.Contact)
        }
    )
}