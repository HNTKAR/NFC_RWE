package com.example.nfc_rwe.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Upload
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppRoleModel(val context: String, val icon: ImageVector) {
    READ(context = "読み込み", icon = Icons.Filled.Download),
    WRITE(context = "書き込み", icon = Icons.Filled.Upload),
    EMULATE(context = "エミュレート", icon = Icons.Filled.Nfc)
}
