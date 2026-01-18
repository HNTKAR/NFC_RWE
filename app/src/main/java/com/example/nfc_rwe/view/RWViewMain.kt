package com.example.nfc_rwe.view

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nfc_rwe.viewmodel.NfcViewModel

@Composable
fun RWViewMain(
    modifier: Modifier = Modifier, data: @Composable (modifier: Modifier, nvm: NfcViewModel) -> Unit
) {
    val nvm: NfcViewModel = viewModel()
    if (LocalInspectionMode.current) {
        Log.d("ReadView", "You are in a preview")
    } else {
        StartNFC(nvm)
    }
    data(modifier, nvm)
}