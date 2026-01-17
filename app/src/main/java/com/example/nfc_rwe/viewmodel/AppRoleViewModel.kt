package com.example.nfc_rwe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nfc_rwe.model.AppRoleModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppRoleViewModel : ViewModel() {
    private val _mode = MutableStateFlow(AppRoleModel.READ)
    val mode: StateFlow<AppRoleModel> = _mode.asStateFlow()

    fun setMode(mode: AppRoleModel) {
        _mode.value = mode
    }

    fun getModeList(): List<AppRoleModel> {
        return AppRoleModel.entries
    }
}
