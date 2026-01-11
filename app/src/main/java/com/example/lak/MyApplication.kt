package com.example.lak

import android.app.Application
import androidx.room.Room
import com.example.lak.bluetoothManager.BluetoothConnect
import com.example.lak.data.AppDatabase

class MyApplication : Application() {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
    lateinit var bluetoothConnect: BluetoothConnect
    override fun onCreate(){
        super.onCreate()
        bluetoothConnect = BluetoothConnect(
            context = this,
            onDeviceFound = {},
            onConnectionStateChanged = {}
        )
    }
}