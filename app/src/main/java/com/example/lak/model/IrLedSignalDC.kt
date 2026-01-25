package com.example.lak.model



data class IrLedSignalDC(

    val name: String,
    val protocol: String?,
    val address: Long?,
    val command: Long?,
    val numberOfBits: Int?,
    val rawData: String?
)
