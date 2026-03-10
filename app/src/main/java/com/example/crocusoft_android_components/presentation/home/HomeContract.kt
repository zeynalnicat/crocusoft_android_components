package com.example.crocusoft_android_components.presentation.home

sealed interface HomeContract {

    sealed interface Intent{
        data class SetName(val name:String):Intent

        data object Submit: Intent
    }

    data class State(
        val name:String = ""
    )
}