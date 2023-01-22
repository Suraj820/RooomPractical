package com.example.rooompractical

import com.example.rooompractical.model.Subscribers

class SubscriberRepository(private val dao: SubscriberDAO) {
    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscribers: Subscribers):Long{
        return dao.insertSubscriber(subscribers)
    }
    suspend fun update(subscribers: Subscribers):Int{
        return dao.updateSubscriber(subscribers)
    }
    suspend fun delete(subscribers: Subscribers):Int{
        return dao.deleteSubscriber(subscribers)
    }
    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}