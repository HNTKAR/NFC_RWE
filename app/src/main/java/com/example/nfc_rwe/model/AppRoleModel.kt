package com.example.nfc_rwe.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Upload
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppRoleModel(val context: String, val icon: ImageVector) {
    READ("読み込み", Icons.Filled.Download),
    WRITE("書き込み", Icons.Filled.Upload),
    EMULATE("エミュレート", Icons.Filled.Nfc)
}
