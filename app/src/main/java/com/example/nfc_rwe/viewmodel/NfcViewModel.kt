package com.example.nfc_rwe.viewmodel

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.NdefFormatable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nfc_rwe.model.NdefRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NfcViewModel(private val nr: NdefRepository = NdefRepository()) : ViewModel() {
    val tagIndex = "------tag------\n"
    private val _cardInfo = MutableStateFlow("")
    val cardInfo: StateFlow<String> = _cardInfo.asStateFlow()
    
    fun getCardAllData(tag: Tag) {
        viewModelScope.launch {
            nr.getData(tag).collect {
                _cardInfo.value = tagIndex
                _cardInfo.value += it.first().tech.joinToString(separator = " ")
                _cardInfo.value += "\n"
                it.forEachIndexed { index, record ->
                    _cardInfo.value += "---message[$index]---\n"
                    _cardInfo.value += "type: ${record.type.typeName}\n"
                    _cardInfo.value += "payload: ${record.payload}\n"
                }
            }
        }
    }

    fun nfcDebug(tag: Tag) {
        Log.d("ReadView", "Card detected")
        Log.d("ReadView", "Tag Tech List: ${tag.techList.asList()}")
    }

    fun read(tag: Tag) {
        val tag = tag
        nfcDebug(tag)
        getCardAllData(tag)
    }

    private fun format(tag: Tag) {
        val ndef = NdefFormatable.get(tag)
        if (ndef == null) {
            Log.d("ReadView", "Can't format message")
        } else {
            Log.d("ReadView", "Format Message")
            val record = NdefRecord.createTextRecord("en", "Formatted by NFC_RWE")
            val message = NdefMessage(record)
            ndef.connect()
            ndef.format(message)
            ndef.close()
        }
    }

}