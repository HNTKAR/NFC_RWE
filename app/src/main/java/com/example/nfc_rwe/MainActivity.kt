package com.example.nfc_rwe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nfc_rwe.ui.theme.NFC_RWETheme

class MainActivity : ComponentActivity() {
    val ma = MainView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NFC_RWETheme {
                ma.Main()
            }
        }
    }
}