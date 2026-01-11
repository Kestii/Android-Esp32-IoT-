package com.example.lak.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bluetooth_devices")
data class BluetoothDevice(
    @PrimaryKey val macAddress : String, // cannot be duplicate values with primary key
    val deviceName : String
)
