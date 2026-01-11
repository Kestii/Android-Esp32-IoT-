package com.example.lak.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lak.data.dao.BluetoothDeviceDao

class BtScanViewModelFactory(
    private val dao: BluetoothDeviceDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BtScanViewModel::class.java)) {
            return BtScanViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
