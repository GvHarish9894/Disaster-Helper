package com.techgv.disasterhelper.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techgv.disasterhelper.data.dao.MessageDao
import com.techgv.disasterhelper.data.entity.Message

@Database(
    version = 1,
    entities = [Message::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
