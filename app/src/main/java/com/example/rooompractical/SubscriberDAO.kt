package com.example.rooompractical

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rooompractical.model.Subscribers
import kotlinx.coroutines.flow.Flow

interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscribers: Subscribers): Long

    @Update
    suspend fun updateSubscriber(subscribers: Subscribers) : Int

    @Delete
    suspend fun deleteSubscriber(subscribers: Subscribers) : Int

    @Query("DELETE FROM subscribers_table")
    fun deleteAll():Int

    @Query("SELECT * FROM subscribers_table")
    fun getAllSubscribers(): Flow<List<Subscribers>>


}