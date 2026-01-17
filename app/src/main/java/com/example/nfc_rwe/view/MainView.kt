package com.example.nfc_rwe.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nfc_rwe.model.AppRoleModel
import com.example.nfc_rwe.viewmodel.AppRoleViewModel

class MainView() {
    @Composable
    fun Main() {
        val vm: AppRoleViewModel = viewModel()
        val mode by vm.mode.collectAsStateWithLifecycle()
        Scaffold(bottomBar = { BottomBar(vm, mode) }) { innerPadding ->
            Text(
                text = mode.context,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }

    @Composable
    private fun BottomBar(vm: AppRoleViewModel, mode: AppRoleModel) {
        NavigationBar {
            vm.getModeList().forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.name) },
                    label = { Text(item.name) },
                    selected = mode == item,
                    onClick = { vm.setMode(item) }
                )
            }
        }
    }
}
