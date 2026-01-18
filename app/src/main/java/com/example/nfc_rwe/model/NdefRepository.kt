package com.example.nfc_rwe.model

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NdefRepository {


    fun getData(tag: Tag): Flow<List<NdefModel>> = flow {
        val ndef = Ndef.get(tag)
        if (ndef != null) {
            ndef.connect()
            val message = ndef.ndefMessage
            ndef.close()
            if (message == null) return@flow
            val cardData = decode(message)
            //対応プロトコルはカードで一意のため後から設定する
            for (data in cardData) {
                data.tech = getTagInfo(tag)
            }
            emit(cardData)
        } else {
            Log.d("NdefRepository", "Tag is not ndef")
        }
    }

    fun getTagInfo(tag: Tag): List<String> {
        val tagInfo = mutableListOf<String>()
        tag.techList?.forEach {
            tagInfo += it.split(".")[3]
        }
        return tagInfo
    }


    fun decode(msg: NdefMessage): List<NdefModel> {
        val cardData = mutableListOf<NdefModel>()
        msg.records.forEach {
            var type: NdefTypeModel
            var payload = ""
            when (it.type.toString(Charsets.UTF_8)) {
                NdefRecord.RTD_URI.toString(Charsets.UTF_8) -> {
                    payload = it.toUri().toString()
                    type = NdefTypeModel.URI
                }

                NdefRecord.RTD_TEXT.toString(Charsets.UTF_8) -> type = NdefTypeModel.TEXT
                else -> type = NdefTypeModel.OTHER
            }
            if (payload == "") {
                payload = String(it.payload)
            }
            val tagInfo: List<String> = listOf()
            cardData += NdefModel(tagInfo, type, payload)
        }
        return cardData
    }

    fun debugNdefData(cardData: List<NdefModel>) {
        cardData.forEach {
            Log.d("NdefRepository", "type: ${it.type}, payload: ${it.payload}")
        }
    }
}