package com.example.lak.bluetoothManager

import android.annotation.SuppressLint
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.os.Build
import android.util.Log
import java.util.UUID


class BluetoothCommandManager(
    private val bluetoothGatt: BluetoothGatt,
    private val writeCharacteristic: BluetoothGattCharacteristic,
    private val authCharacteristic: BluetoothGattCharacteristic,
    private val cmdCharacteristic: BluetoothGattCharacteristic
) {


    private var notifyEnabled = false

    fun sendCommand(command: String){
        writeCharacteristic(cmdCharacteristic, command)

    }
    fun authenticate(password: String) {
        writeCharacteristic(authCharacteristic, password)
    }
    @SuppressLint("MissingPermission")
    fun enableNotify(){
        if (notifyEnabled) {
            return
        }

        bluetoothGatt.setCharacteristicNotification(cmdCharacteristic,true)
        val cccd = cmdCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"))
        cccd.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
        bluetoothGatt.writeDescriptor(cccd)
        notifyEnabled = true
    }

    fun receiveNotify(data: String){
        Log.d("BLE", "Notify data: $data.")
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
            // API < 33
            characteristic.value = data
            bluetoothGatt.writeCharacteristic(characteristic)
        }
    }

}