package com.android.starterpack.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.android.starterpack.data.local.dao.ContributorDao
import com.android.starterpack.data.local.entities.ContributorEntity

@Database(entities = [ContributorEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contributorDao(): ContributorDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "starter_pack_database"
                )
                    .build()
                this.instance = instance
                instance
            }
        }
    }
}
