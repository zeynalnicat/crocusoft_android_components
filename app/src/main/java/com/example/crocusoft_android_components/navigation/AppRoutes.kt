package com.example.crocusoft_android_components.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {


    @Serializable
    data object Second

    @Serializable
    data object Music : AppRoutes

    @Serializable
    data object Contact : AppRoutes


}