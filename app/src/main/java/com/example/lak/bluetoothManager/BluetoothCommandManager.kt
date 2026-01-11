package com.example.lak.bluetoothManager

import android.annotation.SuppressLint
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.os.Build

class BluetoothCommandManager(
    private val bluetoothGatt: BluetoothGatt,
    private val writeCharacteristic: BluetoothGattCharacteristic,
    private val authCharacteristic: BluetoothGattCharacteristic,
    private val cmdCharacteristic: BluetoothGattCharacteristic
) {


    fun sendCommand(command: String){
        writeCharacteristic(cmdCharacteristic, command)

    }
    fun authenticate(password: String) {
        writeCharacteristic(authCharacteristic, password)
    }

    @SuppressLint("MissingPermission")
    private fun writeCharacteristic(characteristic: BluetoothGattCharacteristic, command: String){
        val data = command.toByteArray(Charsets.UTF_8)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // API 33+
            bluetoothGatt.writeCharacteristic(
                characteristic,
                data,
                BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
            )
        } else {
            // API < 33 (deprecated mutta pakollinen)
            characteristic.value = data
            bluetoothGatt.writeCharacteristic(characteristic)
        }
    }
}