package com.example.lak.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.lak.viewmodel.BtScanViewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lak.model.BluetoothConnectionState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lak.MyApplication
import com.example.lak.bluetoothManager.BluetoothConnect
import com.example.lak.viewmodel.BtScanViewModelFactory

@Composable
fun ScanBtDevicesScreen(

){


    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val dao =  app.database.bluetoothDeviceDao()

    val viewModel: BtScanViewModel = viewModel(
        factory = BtScanViewModelFactory(dao)
    )

    val bluetoothConnect = app.bluetoothConnect

    LaunchedEffect(Unit) {
        bluetoothConnect.setCallbacks(
            onDeviceFound = { viewModel.onDeviceFound(it) },
            onConnectionStateChanged = { viewModel.onConnectionStateChanged(it) }
        )
    }

    val connectionState by viewModel.connectionState.collectAsState()



    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        Text(text = when(connectionState){
            BluetoothConnectionState.CONNECTED ->   "Connected "
            BluetoothConnectionState.CONNECTING -> "Connecting..."
            BluetoothConnectionState.DISCONNECTED -> "Not connected "
        })
        Button(onClick = {bluetoothConnect.scanBluetoothDevices()}) {
            Text(text = "find bluetoothDevices")
        }
        Button(onClick = {bluetoothConnect.stopScanBluetoothDevices()}) {
            Text("Stop scanning for bluetooth devices")
        }
        val devices by viewModel.devices.collectAsState()

        LazyColumn { items(devices) { device ->
            val name = device.name
            val mac = device.mac
            Text(text = "name: $name mac: $mac")

            // connect and save the new device.
            if(!mac.isNullOrEmpty()){
                Button(onClick = {
                    bluetoothConnect.connectToBtDevice(mac)
                    Log.d("insert BT", "testataan")
                    viewModel.insertBluetoothDevice(device)
                }) {
                    Text("Connect to device $name")
                }
            }

            }
        }
    }
}