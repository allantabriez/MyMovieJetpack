package com.example.jetpacksubmission.di

import android.content.Context
import com.example.jetpacksubmission.data.source.CatalogueRepository
import com.example.jetpacksubmission.data.source.local.LocalDataSource
import com.example.jetpacksubmission.data.source.local.room.CatalogueDatabase
import com.example.jetpacksubmission.data.source.remote.RemoteDataSource
import com.example.jetpacksubmission.utils.LoopJ

object Injection {
    fun provideRepository(context: Context): CatalogueRepository{
        val database = CatalogueDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(LoopJ())
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        return CatalogueRepository.getInstance(remoteDataSource, localDataSource)
    }
}