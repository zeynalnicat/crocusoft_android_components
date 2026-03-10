package com.example.crocusoft_android_components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crocusoft_android_components.presentation.contact.ContactView
import com.example.crocusoft_android_components.presentation.music.MusicView
import com.example.crocusoft_android_components.presentation.second.SecondView


@Composable
fun MainHost(innerPadding: PaddingValues, name: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.Second) {
        composable<AppRoutes.Second> { SecondView(innerPadding, name,navController) }
        composable<AppRoutes.Music> { MusicView(innerPadding,navController) }
        composable<AppRoutes.Contact> { ContactView(innerPadding) }

    }
}
