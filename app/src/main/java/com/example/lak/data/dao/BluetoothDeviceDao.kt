package com.example.lak.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lak.data.entity.BluetoothDevice
import kotlinx.coroutines.flow.Flow

@Dao
interface BluetoothDeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBluetoothDevice(device: BluetoothDevice)

    @Query("SELECT * FROM bluetooth_devices")
    fun getBluetoothDevices(): Flow<List<BluetoothDevice>>
}