package com.example.lak.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.lak.bluetoothManager.BluetoothConnect

class IrLedScreenViewModel(
    private val bluetoothConnect : BluetoothConnect
): ViewModel() {

    fun addManuallyNewIrLedSignal(){

    }
    fun ScanNewIrLedSignal(){

    }
    fun sendIrLedSignal(){
        val manager = bluetoothConnect.commandManager
        if(manager!=null){
            manager.sendCommand("hahaaa")
        }
        else{
            Log.d("IrLedScreenViewModel", "bluetoothmanager ei oo valmis lähettää komentoa")
        }

    }
    fun addCommand(text: String){}
}