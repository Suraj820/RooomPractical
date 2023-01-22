package com.example.rooompractical

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rooompractical.model.Subscribers
import kotlinx.coroutines.launch

class SubscriberViewModel (private val subscriberRepository: SubscriberRepository) : ViewModel(){
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()


    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()


    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(): Unit {
        val name = inputName.value!!
        val email = inputEmail.value!!
        insertSubscriber(
            Subscribers(0,name,email)
        )
        inputName.value =""
        inputEmail.value =""
    }

    private fun insertSubscriber(subscribers: Subscribers) {
        viewModelScope.launch {
            subscriberRepository.insert(subscribers)
        }
    }
}