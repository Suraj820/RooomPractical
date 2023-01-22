package com.example.rooompractical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rooompractical.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val doa = SubscriberDataBase.getInstance(this).subscriberDAO
        val repository = SubscriberRepository(doa)
        val factory = SubscriberViewModelFactory(repository)
        val subscriberViewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
        binding.viewModel = subscriberViewModel
        binding.lifecycleOwner = this


    }
}