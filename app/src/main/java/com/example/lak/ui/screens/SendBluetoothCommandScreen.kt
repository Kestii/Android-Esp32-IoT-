package com.example.lak.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lak.MyApplication
import com.example.lak.viewmodel.SendBluetoothCommandViewModel
import com.example.lak.viewmodel.SendBluetoothCommandViewModelFactory

@Composable
fun SendBluetoothCommandScreen(
    onNavigateToIrLedScreen : ()->Unit

){

    val context = LocalContext.current
    val app =context.applicationContext as MyApplication
    val bluetoothConnect = app.bluetoothConnect
    val viewModel: SendBluetoothCommandViewModel = viewModel(
        factory = SendBluetoothCommandViewModelFactory(bluetoothConnect)
    )
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),

    ){
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Button(onClick = {viewModel.sendCommand("xxx1")}) {
            Text("Send x command")
        }
        Button(onClick = {viewModel.authenticate("password")}) {
            Text("send another command here")
        }
        Button(onClick = {onNavigateToIrLedScreen()}) {
            Text(text = "Ir led functionality")
        }

    }

}