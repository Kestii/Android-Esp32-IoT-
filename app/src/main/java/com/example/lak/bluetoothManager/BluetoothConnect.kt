package com.example.lak.bluetoothManager

import android.annotation.SuppressLint
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import com.example.lak.model.BluetoothConnectionState
import com.example.lak.model.ScannedBtDevice

import com.example.lak.permissions.BluetoothPermissions
import android.bluetooth.BluetoothGattCharacteristic
import android.util.Log
import java.util.UUID


class BluetoothConnect (
    private val context: Context,
    private var onDeviceFound: (ScannedBtDevice) -> Unit,
    private var onConnectionStateChanged: (BluetoothConnectionState)->Unit
){
    fun setCallbacks(
        onDeviceFound: (ScannedBtDevice) -> Unit,
        onConnectionStateChanged: (BluetoothConnectionState) -> Unit
    ) {
        this.onDeviceFound = onDeviceFound
        this.onConnectionStateChanged = onConnectionStateChanged
    }


    private val SERVICE_UUID =
        UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b")



    private val AUTH_CHAR_UUID =
        UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    private val CMD_CHAR_UUID =
        UUID.fromString("beb5483e-36e1-4688-b7f5-ea07361b26a8")
    private var authCharacteristic: BluetoothGattCharacteristic? = null
    private var cmdCharacteristic: BluetoothGattCharacteristic? = null



    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter
    private val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
    private var isScanning = false
    var commandManager: BluetoothCommandManager? = null





    val leScanCallback = object : ScanCallback(){
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult?){
            result?.device?.name ?: return
            val device = ScannedBtDevice(
                result.device.name?: "Unknown",
                result.device.address

            )

            onDeviceFound(device)
        }

    }

    @SuppressLint("MissingPermission")
    fun scanBluetoothDevices(){
        val hasPermissions = BluetoothPermissions.hasAllPermissions(context)
        if(!hasPermissions){
            return
        }
        else if(bluetoothAdapter.isEnabled){
            bluetoothLeScanner.startScan(leScanCallback)
            isScanning = true
        }
    }

    @SuppressLint("MissingPermission")
    fun stopScanBluetoothDevices(){
        if(isScanning) {
            bluetoothLeScanner.stopScan(leScanCallback)
            isScanning = false
        }
    }
    private var bluetoothGatt: BluetoothGatt? = null
    @SuppressLint("MissingPermission")
    fun connectToBtDevice(mac: String){
        if(mac.isEmpty()){
            return
        }
        else{
            onConnectionStateChanged(BluetoothConnectionState.CONNECTING)
            stopScanBluetoothDevices()
            val device = bluetoothAdapter.getRemoteDevice(mac)
            bluetoothGatt = device.connectGatt(context, false, bluetoothGattCallback)
        }
    }

    @SuppressLint("MissingPermission")
    fun disconnectFromBtDevice(){
        bluetoothGatt?.disconnect()
        bluetoothGatt?.close()
        bluetoothGatt = null

    }

    private val bluetoothGattCallback = object : BluetoothGattCallback() {
        @SuppressLint("MissingPermission")
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            if(newState == BluetoothProfile.STATE_CONNECTED){
                onConnectionStateChanged(BluetoothConnectionState.CONNECTED)
                bluetoothGatt?.discoverServices()

            }
            else if (newState == BluetoothProfile.STATE_DISCONNECTED){
                gatt?.close()
                bluetoothGatt = null
                commandManager = null

                onConnectionStateChanged(BluetoothConnectionState.DISCONNECTED)
            }
        }
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                val service = gatt?.getService(SERVICE_UUID)
                //Log.d("BLE", "Service found: $service")

                authCharacteristic = service?.getCharacteristic(AUTH_CHAR_UUID)
                cmdCharacteristic = service?.getCharacteristic(CMD_CHAR_UUID)

                if (gatt != null && authCharacteristic != null && cmdCharacteristic != null) {
                    commandManager = BluetoothCommandManager(
                        bluetoothGatt = gatt,
                        writeCharacteristic = cmdCharacteristic!!,
                        authCharacteristic = authCharacteristic!!,
                        cmdCharacteristic = cmdCharacteristic!!
                    )


                }
            }
        }
    }





}