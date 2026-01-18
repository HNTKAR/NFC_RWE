package com.example.nfc_rwe.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nfc_rwe.viewmodel.NfcViewModel


@Composable
fun WriteView(modifier: Modifier = Modifier, nvm: NfcViewModel = viewModel()) {
    val cardInfo by nvm.cardInfo.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Write Mode", fontSize = 30.sp, color = Color.Red)
        Row {
            Text("URL")
            TextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                value = cardInfo,
                onValueChange = { }
            )
        }
    }
}

@Preview
@Composable
fun WriteViewPreview() {
    WriteView()
}

