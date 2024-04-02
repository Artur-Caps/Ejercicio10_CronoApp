package com.mexiti.cronoapp.repository

import com.mexiti.cronoapp.model.Cronos
import com.mexiti.cronoapp.room.CronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronosRepository@Inject constructor(
    private val cronosDatabaseDao:CronosDatabaseDao) {
    suspend fun addCrono(crono:Cronos)= cronosDatabaseDao.insert(crono)

    suspend fun  updateCrono (cronos: Cronos)= cronosDatabaseDao.update(crono = cronos)

    suspend fun deleteCrono (cronos: Cronos) = cronosDatabaseDao.delete(crono = cronos)

    fun getAllcronos(): Flow<List<Cronos>> = cronosDatabaseDao
        .getCronos()
        .flowOn(Dispatchers.IO)
        .conflate()

    fun getCronByID(id:Long):Flow<Cronos> = cronosDatabaseDao
        .getCronosById(id)
        .flowOn(Dispatchers.IO)
        .conflate()

}