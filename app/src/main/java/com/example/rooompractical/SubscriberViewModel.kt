package com.example.rooompractical

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.rooompractical.model.Subscribers
import kotlinx.coroutines.launch

class SubscriberViewModel (private val subscriberRepository: SubscriberRepository) : ViewModel(){
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputPhone = MutableLiveData<String>()


    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
    get() = statusMessage

    val subscribers = subscriberRepository.subscribers

    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscribers


    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(): Unit {

        if (inputName.value == null){
            statusMessage.value = Event("Please enter subscriber's name")
        }else if (inputEmail.value == null){
            statusMessage.value = Event("Please enter subscriber's email")
        }else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
            statusMessage.value = Event("Please enter a correct email address")
        }else if (inputPhone.value == null){
            statusMessage.value = Event("Please enter subscriber's phone")
        }else if (!Patterns.PHONE.matcher(inputPhone.value!!).matches()){
            statusMessage.value = Event("Please enter a correct phone number ")
        }else{
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!
                subscriberToUpdateOrDelete.phone = inputPhone.value!!
                updateSubscriber(subscriberToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                val phone = inputPhone.value!!
                insertSubscriber(Subscribers(0, name, email,phone))
                inputName.value = ""
                inputEmail.value = ""
                inputPhone.value = ""
            }
        }


    }

    private fun insertSubscriber(subscribers: Subscribers) {
        viewModelScope.launch {
            val newRowId = subscriberRepository.insert(subscribers)
            if ( newRowId > -1){
                statusMessage.value = Event("Subscriber Inserted Successfully $newRowId")
            }else{
                statusMessage.value = Event("Error Occurred")
            }
        }
    }

    fun getSavedSubscriber() = liveData {
        subscriberRepository.subscribers.collect{
            emit(it)
        }
    }

    private fun  clearAll() = viewModelScope.launch {
        val noOfRowsDeleted = subscriberRepository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            deleteSubscriber(subscriberToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun deleteSubscriber(subscriber: Subscribers) = viewModelScope.launch {
        val noOfRowsDeleted = subscriberRepository.delete(subscriber)
        if (noOfRowsDeleted > 0) {
            inputName.value = ""
            inputEmail.value = ""
            inputPhone.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun initUpdateAndDelete(subscriber: Subscribers){

       inputName.value = subscriber.name
       inputEmail.value = subscriber.email
       inputPhone.value = subscriber.phone
       isUpdateOrDelete = true
       subscriberToUpdateOrDelete = subscriber
       saveOrUpdateButtonText.value = "Update"
       clearAllOrDeleteButtonText.value = "Delete"
   }
    private fun updateSubscriber(subscriber: Subscribers) = viewModelScope.launch {
        val noOfRows = subscriberRepository.update(subscriber)
        if (noOfRows > 0) {
            inputName.value = ""
            inputEmail.value = ""
            inputPhone.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

}