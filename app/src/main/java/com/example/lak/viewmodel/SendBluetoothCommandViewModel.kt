package com.example.lak.viewmodel
import androidx.lifecycle.ViewModel
import com.example.lak.bluetoothManager.BluetoothConnect

class SendBluetoothCommandViewModel(
    private val bluetoothConnect: BluetoothConnect
): ViewModel() {



    fun sendCommand(command: String){
        val manager = bluetoothConnect.commandManager
        if (manager != null) {
            manager.sendCommand(command)
        } else {
            // Command manager ei ole vielä valmis
            println("Command manager ei ole valmis. Odota palveluiden löytymistä.")
        }
    }
    fun authenticate(command: String){
        val manager = bluetoothConnect.commandManager
        if (manager != null) {
            manager.authenticate(command)
        } else {
            println("Command manager ei ole valmis. Odota palveluiden löytymistä.")
        }
    }
}