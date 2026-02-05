package com.example.lak.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lak.bluetoothManager.BluetoothConnect
import com.example.lak.data.dao.IRLedSignalDao

class IrLedScreenViewModelFactory(
    private val bluetoothConnect: BluetoothConnect,
    private val dao: IRLedSignalDao
) : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(IrLedScreenViewModel::class.java)){
            return IrLedScreenViewModel(bluetoothConnect,dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}