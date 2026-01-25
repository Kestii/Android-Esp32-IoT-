package com.example.lak.data.dao
import android.icu.text.MessagePattern.ArgType.SELECT
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lak.data.entity.IRLedSignal


@Dao
interface IRLedSignalDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIRLedSignal(irSignal: IRLedSignal)

    @Query ("SELECT * FROM IrLedSignals")
    fun getIrLedSignal(name: String)
}