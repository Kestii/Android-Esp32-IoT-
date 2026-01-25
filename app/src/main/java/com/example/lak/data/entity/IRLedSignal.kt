package com.example.lak.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity (tableName = "IrLedSignals")
data class IrLedSignal(
    @PrimaryKey val name: String,
    val protocol: String?,
    val address: Long?,
    val command: Long?,
    val numberOfBits: Int?,
    val rawData: String?
)

