package com.example.rooompractical

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rooompractical.model.Subscribers
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscribers) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscribers) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscribers) :Int

    @Query("DELETE FROM subscribers_table")
    suspend fun deleteAll():Int

    @Query("SELECT * FROM subscribers_table")
    fun getAllSubscribers():Flow<List<Subscribers>>
}