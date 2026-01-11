package com.example.lak.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider



import com.example.lak.bluetoothManager.BluetoothConnect

class SendBluetoothCommandViewModelFactory(
    private val bluetoothConnect:BluetoothConnect
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendBluetoothCommandViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SendBluetoothCommandViewModel(bluetoothConnect) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}