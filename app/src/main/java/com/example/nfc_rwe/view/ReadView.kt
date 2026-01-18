package com.example.nfc_rwe.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nfc_rwe.viewmodel.NfcViewModel

@Composable
fun ReadViewMain(modifier: Modifier = Modifier) {
//    var adapter: NfcAdapter? = getNfcAdapter()
    val nvm: NfcViewModel = viewModel()
    val cardInfo by nvm.cardInfo.collectAsStateWithLifecycle()
    if (LocalInspectionMode.current) {
        Log.d("ReadView", "You are in a preview")
    } else {
        StartNFC(nvm)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Read Mode", fontSize = 30.sp, color = Color.Green)
        TextField(
            modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            maxLines = 4,
            value = cardInfo,
            onValueChange = { },
            readOnly = true
        )
    }

}

@Preview
@Composable
fun ReadViewPreview() {
    ReadViewMain()
}

