package com.example.lak.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.lak.bluetoothManager.BluetoothConnect
import com.example.lak.model.IrLedSignalDC


class IrLedScreenViewModel(
    private val bluetoothConnect : BluetoothConnect
): ViewModel() {
    //protocol: String?, address: Long?, command: Long?, numberOfBits: Int?, rawData: String?
    fun addManuallyNewIrLedSignal(irLedSignal: IrLedSignalDC){ // DC = data class tässä ettei mee sekasi muitte kaa

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