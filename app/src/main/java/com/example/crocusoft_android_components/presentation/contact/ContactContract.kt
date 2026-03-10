package com.example.crocusoft_android_components.presentation.contact

import com.example.crocusoft_android_components.domain.ContactModel

sealed interface ContactContract {

    sealed interface Intent {
        data object FetchContact: Intent
    }

    data class State(
        val contacts: List<ContactModel> = emptyList(),
        val isLoading: Boolean = false,
    )

}