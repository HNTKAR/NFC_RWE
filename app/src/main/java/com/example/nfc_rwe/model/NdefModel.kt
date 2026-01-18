package com.example.nfc_rwe.model

data class NdefModel(
    var tech: List<String>,
    val type: NdefTypeModel,
    val payload: String
)
