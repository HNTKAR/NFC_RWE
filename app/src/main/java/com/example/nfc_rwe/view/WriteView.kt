package com.example.nfc_rwe.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        TextField(
            label = { Text("payload") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            value = cardInfo,
            onValueChange = { }
        )
    }
    CreateDialog()
}

@Composable
fun CreateDialog() {
    var isEnable by remember { mutableStateOf(true) }
    val dataText = listOf("A", "B", "C")
    var dataSelected = listOf(false, true, false)
    if (isEnable) {
        AlertDialog(
            title = {
                Text(text = "タイプを選択")
            },
            text = {
                Column(modifier = Modifier.selectableGroup()) {
                    Text(text = "タイプを選択してください")
                    dataText.forEachIndexed { index, txt ->
                        Row(
                            modifier = Modifier
                                .selectable(
                                    role = Role.RadioButton,
                                    selected = true,// true in dataSelected,
                                    onClick = {
                                        dataSelected = dataSelected.mapIndexed { i, _ ->
                                            i == index
                                        }

                                    },
                                )
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = dataSelected[index], onClick = null)
                            Text(txt)
                        }
                    }
                }
            },
            onDismissRequest = {
                isEnable = false
            },
            dismissButton = {
                TextButton(
                    onClick = {
                    }
                ) {
                    Text("戻る")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isEnable = false
                    }
                ) {
                    Text("決定")
                }
            }
        )
    }

}

@Preview
@Composable
fun WriteViewPreview() {
    WriteView()
}

