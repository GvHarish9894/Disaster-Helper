package com.techgv.disasterhelper.repository

import com.techgv.disasterhelper.data.entity.Message
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun loadData(): Flow<Message?>
}
