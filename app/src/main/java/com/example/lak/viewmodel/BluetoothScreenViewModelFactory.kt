package com.example.lak.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lak.data.dao.BluetoothDeviceDao

class BluetoothScreenViewModelFactory(
    private val dao: BluetoothDeviceDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BluetoothScreenViewModel::class.java)){
            return BluetoothScreenViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}