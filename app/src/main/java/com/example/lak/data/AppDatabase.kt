package com.example.lak.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lak.data.dao.BluetoothDeviceDao
import com.example.lak.data.dao.IRLedSignalDao
import com.example.lak.data.entity.BluetoothDevice
import com.example.lak.data.entity.IRLedSignal

@Database(
    entities = [BluetoothDevice::class, IRLedSignal::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase (){
    abstract fun bluetoothDeviceDao(): BluetoothDeviceDao
    abstract fun IRLedSignalDao(): IRLedSignalDao
}