package com.example.lak.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lak.MyApplication
import com.example.lak.data.entity.IrLedSignal
import com.example.lak.model.IrLedSignalDC
import com.example.lak.viewmodel.IrLedScreenViewModel
import com.example.lak.viewmodel.IrLedScreenViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrLedScreen(){

    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val daoIr = (context.applicationContext as MyApplication).database.IRLedSignalDao()
    val viewModel: IrLedScreenViewModel = viewModel(factory = IrLedScreenViewModelFactory(app.bluetoothConnect,daoIr))
    var showSheet by remember { mutableStateOf(false) }
    var nameOfIrLedSignal by remember { mutableStateOf("") }
    var irLedProtocolName by remember{mutableStateOf("")}
    var irLedAddress by remember { mutableStateOf("") }
    var irLedSignalcommand by remember { mutableStateOf("") }
    var irLedSignalNumberOfBits by remember { mutableStateOf("") }
    var irLedSignalRawData by remember { mutableStateOf("") }
    val irLedSignals by viewModel.allSavedIrSignals.collectAsState()
    var savedIrLedSignalSheet by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(20.dp)
    ){


        Button(onClick = {showSheet = true}) {
            Text(text = "add Ir led signal manually")
        }
        LazyColumn (
            modifier = Modifier.padding(16.dp)
        ){
            items(irLedSignals){irLedSignal ->
                Button(onClick = {savedIrLedSignalSheet = true}) {
                    Text("Send/Modify IrLedSignal: ${irLedSignal.name}")
                }
                Text("Name: ${irLedSignal.name}")
                Text("address: ${irLedSignal.address}")
            }
        }

    }
    if(savedIrLedSignalSheet){
        ModalBottomSheet(onDismissRequest = {savedIrLedSignalSheet = false}) { }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("New IR Command", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = nameOfIrLedSignal,
                    onValueChange = { nameOfIrLedSignal = it },
                    label = { Text("IR Led signal name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = irLedProtocolName,
                    onValueChange = {irLedProtocolName = it},
                    label = {Text("Ir Led Protocol")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = irLedAddress,
                    onValueChange = {irLedAddress = it},
                    label = {Text("Ir Led Address")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = irLedSignalcommand,
                    onValueChange = {irLedSignalcommand = it},
                    label = {Text("Ir Led Command")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = irLedSignalNumberOfBits,
                    onValueChange = {irLedSignalNumberOfBits = it},
                    label = {Text("Ir led Number of bits")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = irLedSignalRawData,
                    onValueChange = {irLedSignalRawData = it},
                    label = {Text("Raw Data")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            val irLedSignalToBeAdded =  IrLedSignal(
                                name = nameOfIrLedSignal,
                                protocol = irLedProtocolName.ifBlank { null },
                                address = irLedAddress.toLongOrNull(),
                                command = irLedSignalcommand.toLongOrNull(),
                                numberOfBits = irLedSignalNumberOfBits.toIntOrNull(),
                                rawData = irLedSignalRawData.ifBlank { null })
                            viewModel.addManuallyNewIrLedSignal(irLedSignalToBeAdded)

                            nameOfIrLedSignal = ""
                            irLedProtocolName = ""
                            irLedAddress = ""
                            irLedSignalcommand =""
                            irLedSignalNumberOfBits =""
                            irLedSignalRawData =""

                            showSheet = false
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Save")
                    }

                    Button(
                        onClick = { showSheet = false },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}


// tarvitaan:
//- komentojen lähetys
// - uusien tallennus
//- poistaminen
//-