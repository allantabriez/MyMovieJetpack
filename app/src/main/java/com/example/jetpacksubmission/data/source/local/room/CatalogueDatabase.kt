package com.example.jetpacksubmission.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpacksubmission.data.source.local.entity.FavoriteMovie
import com.example.jetpacksubmission.data.source.local.entity.FavoriteShow

@Database(entities = [FavoriteMovie::class, FavoriteShow::class], version = 1, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase(){
    abstract fun catalogueDao(): CatalogueDao

    companion object{
        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(context.applicationContext,
            CatalogueDatabase::class.java,
            "Catalogue.db").build()
        }
    }

}