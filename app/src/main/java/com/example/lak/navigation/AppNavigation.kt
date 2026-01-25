package com.example.lak.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import com.example.lak.ui.screens.HomeScreen
import com.example.lak.ui.screens.SettingsScreen
import com.example.lak.ui.screens.BluetoothScreen
import com.example.lak.ui.screens.IrLedScreen
import com.example.lak.ui.screens.ScanBtDevicesScreen
import com.example.lak.ui.screens.SendBluetoothCommandScreen
import com.example.lak.viewmodel.SendBluetoothCommandViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = "home") {
        composable("home"){
            HomeScreen(
                onNavigateToSettings = {navController.navigate("settings")},
                onNavigateToSendBluetoothCommand = {navController.navigate("bluetoothCommandScreen")}
            )
        }
        composable("settings"){
            SettingsScreen(
                onNavigateToBluetooth = {navController.navigate("bluetooth")}
            )
        }
        composable("bluetooth"){
            BluetoothScreen(
                onNavigateToScanBT = {navController.navigate("ScanBtDevicesScreen")}
            )
        }
        composable("ScanBtDevicesScreen"){
            ScanBtDevicesScreen()
        }
        composable("bluetoothCommandScreen"){
            SendBluetoothCommandScreen(
                onNavigateToIrLedScreen = {navController.navigate("irLedScreen")}
            )
        }
        composable("irLedScreen"){
            IrLedScreen()
        }


    }
}