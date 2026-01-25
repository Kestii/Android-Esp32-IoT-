package com.example.lak.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

class IRLedSignal {

    @Entity (tableName = "IrLedSignals")
    data class IrLedSignal(
        @PrimaryKey val name: String,
        val protocol: String
        
    )

}