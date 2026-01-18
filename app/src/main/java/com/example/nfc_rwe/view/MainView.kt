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

@Composable
fun MainView() {
    val vm: AppRoleViewModel = viewModel()
    val mode by vm.mode.collectAsStateWithLifecycle()
    Scaffold(bottomBar = { BottomBar(vm, mode) }) { innerPadding ->
        RWViewMain(
            Modifier.padding(innerPadding),
//            data = { data1, data3 -> ReadView(data1, data3) })
            data = { data1, data3 -> WriteView(data1, data3) })
    }
}

@Composable
private fun BottomBar(vm: AppRoleViewModel, mode: AppRoleModel) {
    NavigationBar {
        vm.getModeList().forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.context) },
                label = { Text(item.context) },
                selected = mode == item,
                onClick = { vm.setMode(item) }
            )
        }
    }
}
