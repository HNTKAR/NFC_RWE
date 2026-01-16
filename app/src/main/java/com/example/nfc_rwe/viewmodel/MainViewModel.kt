package com.example.nfc_rwe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nfc_rwe.model.AppRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _mode = MutableStateFlow(AppRole.READ)
    val mode: StateFlow<AppRole> = _mode.asStateFlow()

    fun setMode(mode: AppRole) {
        _mode.value = mode
    }
}