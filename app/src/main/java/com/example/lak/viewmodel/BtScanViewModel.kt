package com.example.lak.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lak.data.dao.BluetoothDeviceDao
import com.example.lak.data.entity.BluetoothDevice
import com.example.lak.model.BluetoothConnectionState
import com.example.lak.model.ScannedBtDevice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BtScanViewModel(
    private val dao: BluetoothDeviceDao
) : ViewModel() {

    private val _devices = MutableStateFlow<List<ScannedBtDevice>>(emptyList())
    val devices: StateFlow<List<ScannedBtDevice>> = _devices
    private val _connectionState = MutableStateFlow(BluetoothConnectionState.DISCONNECTED)
    val connectionState: StateFlow<BluetoothConnectionState> = _connectionState


    fun onDeviceFound(device: ScannedBtDevice) {
        // Lisää vain jos ei ole jo listassa
        _devices.update { list ->
            if (list.any { it.mac == device.mac }) list else list + device
        }


    }

    fun onConnectionStateChanged(state: BluetoothConnectionState){
        _connectionState.value = state
    }
    fun insertBluetoothDevice(device: ScannedBtDevice){
        viewModelScope.launch {
            if (!device.name.isNullOrEmpty() && !device.mac.isNullOrEmpty()) {
                dao.insertBluetoothDevice(BluetoothDevice(device.mac, device.name))
                Log.d("insert BT", device.name)
            }
        }
    }
}