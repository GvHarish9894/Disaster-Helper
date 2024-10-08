package com.techgv.disasterhelper.di

import android.content.Context
import androidx.room.Room
import com.techgv.disasterhelper.R
import com.techgv.disasterhelper.data.AppDatabase
import com.techgv.disasterhelper.data.RoomDbInitializer
import com.techgv.disasterhelper.data.dao.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        messageDaoProvider: Provider<MessageDao>
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, context.getString(
                R.string.app_name
            )
        ).addCallback(
            /**
             * Attach [RoomDbInitializer] as callback to the database
             */
            RoomDbInitializer(context = context, messageDaoProvider = messageDaoProvider)
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideMessageDao(appDatabase: AppDatabase): MessageDao = appDatabase.messageDao()
}
