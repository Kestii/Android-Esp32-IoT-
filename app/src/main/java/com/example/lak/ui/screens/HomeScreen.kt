package com.example.lak.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lak.ui.theme.LAKTheme
import com.example.lak.R


@Composable
fun HomeScreen(
    onNavigateToSettings : ()->Unit,
    onNavigateToSendBluetoothCommand : ()->Unit
               ){
    Box(modifier = Modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = R.drawable.home1),
            contentDescription = "taustakuva",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = {onNavigateToSettings ()},
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)

        ){
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "settings",
                modifier = Modifier.size(40.dp)

            )
        }
        Button(onClick = {onNavigateToSendBluetoothCommand()},
            modifier = Modifier.align(Alignment.Center)
            ) {
            Text(text = "Send Bluetooth Commands")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    LAKTheme {
        HomeScreen(onNavigateToSettings = {},
            onNavigateToSendBluetoothCommand = {})
    }
}