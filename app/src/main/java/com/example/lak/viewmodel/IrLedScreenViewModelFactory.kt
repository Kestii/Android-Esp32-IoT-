package com.example.lak.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lak.bluetoothManager.BluetoothConnect

class IrLedScreenViewModelFactory(
    private val bluetoothConnect: BluetoothConnect
) : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(IrLedScreenViewModel::class.java)){
            return IrLedScreenViewModel(bluetoothConnect) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}