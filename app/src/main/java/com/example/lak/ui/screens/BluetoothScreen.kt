package com.example.lak.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lak.data.DatabaseBuilder
import com.example.lak.data.entity.BluetoothDevice
import com.example.lak.permissions.BluetoothPermissions
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lak.MyApplication
import com.example.lak.viewmodel.BluetoothScreenViewModel
import com.example.lak.viewmodel.BluetoothScreenViewModelFactory
import com.example.lak.bluetoothManager.BluetoothConnect

@Composable
fun BluetoothScreen(onNavigateToScanBT : ()->Unit
                   ){
    val context = LocalContext.current
    var hasPermission by remember { mutableStateOf(false) } // granted permissions to the app
    val coroutineScope = rememberCoroutineScope()

    val dao = (context.applicationContext as MyApplication).database.bluetoothDeviceDao()
    val viewModel: BluetoothScreenViewModel = viewModel(
        factory = BluetoothScreenViewModelFactory(dao)
    )
    val savedDevices by viewModel.savedDevices.collectAsState()
    val app =context.applicationContext as MyApplication
    val bluetoothConnect = app.bluetoothConnect
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        permissions->
        hasPermission = permissions.values.all { it }
    }


    LaunchedEffect(Unit) {
        hasPermission  = BluetoothPermissions.hasAllPermissions(context)
    }
    fun requestPermissions(){
        val permissions = BluetoothPermissions.getRequiredPermissions()
        permissionLauncher.launch(permissions)
    }


    Column {

        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        if(!hasPermission) {
            Button(onClick = { requestPermissions() }) {
                Text("Bluetooth permissions needed")
            }
        }
        Text(text = "Connect to a new device")

        Text(text = "Saved devices")
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            items(savedDevices) { device ->
                Text("${device.deviceName} (${device.macAddress})")
                Button(onClick = { bluetoothConnect.connectToBtDevice(device.macAddress) }) {
                    Text("Connect to ${device.deviceName}")
                }
            }
        }




        val name = "testilaite"
        val mac = "12345678"

        Button(onClick = {
            coroutineScope.launch {
                dao.insertBluetoothDevice(BluetoothDevice(mac, name))

            }
        }) {
            Text("Add test device")
        }
        Button(onClick = { onNavigateToScanBT() }) {
            Text("Scan New Devices")
        }



    }
}