package com.example.crocusoft_android_components.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {


    private val _state = MutableStateFlow(HomeContract.State())

    val state = _state.asStateFlow()

    fun onIntent(intent: HomeContract.Intent){
        when(intent){
            is HomeContract.Intent.SetName -> {
                viewModelScope.launch {
                    _state.update { it.copy(name = intent.name) }
                }
            }
            HomeContract.Intent.Submit -> {

            }
        }
    }
}