package com.example.saa_apps.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.saa_apps.data.dao.MessageDao
import com.example.saa_apps.data.dao.NoteDao
import com.example.saa_apps.data.entity.MessageEntity
import com.example.saa_apps.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class, MessageEntity::class],  // TAMBAHKAN
    version = 2,  // Naikkan versi jadi 2
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun messageDao(): MessageDao  // TAMBAHKAN
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()  // TAMBAHKAN (karena versi naik)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}