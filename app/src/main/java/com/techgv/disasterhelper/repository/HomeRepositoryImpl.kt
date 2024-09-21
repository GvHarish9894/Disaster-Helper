package com.techgv.disasterhelper.repository

import com.techgv.disasterhelper.data.dao.MessageDao
import com.techgv.disasterhelper.data.entity.Message
import com.techgv.disasterhelper.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val messageDao: MessageDao
) : HomeRepository {
    override fun loadData(): Flow<Message?> {
        return messageDao.getMessage()
    }
}
