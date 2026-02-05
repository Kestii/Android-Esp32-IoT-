package com.example.lak.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lak.bluetoothManager.BluetoothConnect
import com.example.lak.data.dao.IRLedSignalDao
import com.example.lak.data.entity.IrLedSignal
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class IrLedScreenViewModel(
    private val bluetoothConnect : BluetoothConnect,
    private val dao: IRLedSignalDao

): ViewModel() {

    val allSavedIrSignals: StateFlow<List<IrLedSignal>> = dao.getAllIrLedSignals().stateIn(viewModelScope,
        SharingStarted.Eagerly,emptyList())
    //protocol: String?, address: Long?, command: Long?, numberOfBits: Int?, rawData: String?
    fun addManuallyNewIrLedSignal(irLedSignal: IrLedSignal){ // DC = data class tässä ettei mee sekasi muitte kaa
        viewModelScope.launch {
            dao.insertIRLedSignal(irLedSignal)
        }
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