package com.example.crocusoft_android_components.presentation.contact

import android.content.ContentResolver
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.example.crocusoft_android_components.domain.ContactModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ContactViewModel @Inject constructor(val contentResolver: ContentResolver) : ViewModel() {

    private val _state = MutableStateFlow(ContactContract.State())
    val state = _state.asStateFlow()

    fun onIntent(intent: ContactContract.Intent) {
        when (intent) {
            ContactContract.Intent.FetchContact -> getContacts()
        }

    }

    fun getContacts() {
        val contactsList = mutableListOf<ContactModel>()
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )

        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            while (cursor.moveToNext()) {
                val id = cursor.getString(idIndex)
                val name = cursor.getString(nameIndex)
                val number = cursor.getString(numberIndex)
                contactsList.add(ContactModel(id, name, number))
            }
        }

        _state.update { it.copy(contacts = contactsList) }
    }

}