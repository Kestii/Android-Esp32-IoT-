package com.example.lak.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lak.R


@Composable
fun SettingsScreen(onNavigateToBluetooth : ()->Unit){
    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 50.dp)){

        Button(onClick = {onNavigateToBluetooth ()}){
            Row{
                Text("Bluetooth")
                Spacer(modifier = Modifier.width(25.dp))
                Image(
                    painter = painterResource(id = R.drawable.bluetooth_icon),
                    contentDescription = "Bluetooth",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}