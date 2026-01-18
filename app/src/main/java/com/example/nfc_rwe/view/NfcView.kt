package com.example.nfc_rwe.view

import android.nfc.NfcAdapter
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.example.nfc_rwe.viewmodel.NfcViewModel


@Composable
fun StartNFC(nvm: NfcViewModel) {
    val activity = LocalActivity.current
    var isEnableNFC = false
    val adapter = NfcAdapter.getDefaultAdapter(activity)
    DisposableEffect(adapter) {
        if (adapter != null && adapter.isEnabled) {
            Log.d("ReadView", "NFC is enabled")
            isEnableNFC = true
            adapter.enableReaderMode(
                activity,
                { tag ->
                    nvm.read(tag)
                },
                NfcAdapter.FLAG_READER_NFC_A or
                        NfcAdapter.FLAG_READER_NFC_B or
                        NfcAdapter.FLAG_READER_NFC_F or
                        NfcAdapter.FLAG_READER_NFC_V,
                null
            )
        } else {
            Log.d("ReadView", "NFC is disabled")
        }
        onDispose {
            if (adapter != null && isEnableNFC) {
                Log.d("ReadView", "Disable reader mode")
                adapter.disableReaderMode(activity)
            }
        }
    }
}