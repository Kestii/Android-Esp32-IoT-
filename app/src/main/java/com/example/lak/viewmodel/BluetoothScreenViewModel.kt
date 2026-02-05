package com.example.lak.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lak.bluetoothManager.BluetoothConnect
import com.example.lak.data.dao.BluetoothDeviceDao
import com.example.lak.data.entity.BluetoothDevice
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BluetoothScreenViewModel(
    val dao: BluetoothDeviceDao
) : ViewModel() {


    val savedDevices: StateFlow<List<BluetoothDevice>> = dao.getBluetoothDevices()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())


    fun connectToBluetoothDevice(macAddress: String){

    }

}
