package com.example.rooompractical

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rooompractical.model.Subscribers

@Database(entities = [Subscribers::class], version = 2, autoMigrations = [AutoMigration(from = 1 ,to = 2)])
abstract class SubscriberDataBase : RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    companion object {

        private var INSTANCE: SubscriberDataBase? = null

        fun getInstance(ctx: Context): SubscriberDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        ctx.applicationContext,
                        SubscriberDataBase::class.java,
                        "SubscriberDataBase"
                    ).build()
                }
                return instance
            }

        }
    }
}