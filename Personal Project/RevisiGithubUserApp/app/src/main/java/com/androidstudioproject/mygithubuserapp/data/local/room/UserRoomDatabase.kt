package com.androidstudioproject.mygithubuserapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.androidstudioproject.mygithubuserapp.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserRoomDatabase? = null
        fun getInstance(context: Context): UserRoomDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java, "user_database"
                ).addMigrations(migration1to2)
                    .build()
            }

        val migration1to2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create a new table with the updated schema
                database.execSQL(
                    "CREATE TABLE favorite_user_new (" +
                            "username TEXT PRIMARY KEY NOT NULL," +
                            "avatar_url TEXT NOT NULL," +
                            "favorite_yes_or_no INTEGER NOT NULL" +
                            ")"
                )
                // Remove the old table
                database.execSQL("DROP TABLE favorite_user")

                // Rename the new table to the original name
                database.execSQL("ALTER TABLE favorite_user_new RENAME TO favorite_user")
            }
        }
    }

}
