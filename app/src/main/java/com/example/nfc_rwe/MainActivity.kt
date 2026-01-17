package com.example.nfc_rwe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nfc_rwe.ui.theme.NFC_RWETheme
import com.example.nfc_rwe.view.MainView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NFC_RWETheme {
                val ma = MainView()
                ma.Main()
            }
        }
    }
}
