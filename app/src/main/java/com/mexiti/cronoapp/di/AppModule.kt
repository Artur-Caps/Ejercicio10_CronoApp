package com.mexiti.cronoapp.di

import android.content.Context
import androidx.room.Room
import com.mexiti.cronoapp.model.Cronos
import com.mexiti.cronoapp.room.CronosDataBase
import com.mexiti.cronoapp.room.CronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providiesCronosDao(cronosDataBase: CronosDataBase):CronosDatabaseDao{
        return cronosDataBase.cronosDao()
    }
    @Singleton
    @Provides
    fun providiesCronosDatabase(@ApplicationContext context: Context): CronosDataBase{
            return Room.databaseBuilder(
                context = context,
                CronosDataBase::class.java,
                name = "cronos_db"
            ).fallbackToDestructiveMigration()
                .build()
        }
}