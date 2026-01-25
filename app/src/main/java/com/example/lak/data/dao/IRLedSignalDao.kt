package com.example.lak.data.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lak.data.entity.IrLedSignal
import kotlinx.coroutines.flow.Flow


@Dao
interface IRLedSignalDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIRLedSignal(irSignal: IrLedSignal)

    @Query ("SELECT * FROM IrLedSignals WHERE name == :name")
    suspend fun getIrLedSignalByName (name: String): IrLedSignal

    @Query ("SELECT * FROM irledsignals")
    fun getAllIrLedSignals(): Flow<List<IrLedSignal>>


    @Query ("DELETE FROM IrLedSignals WHERE name == :name")
    suspend fun deleteIrLedSignalByName(name: String)
}