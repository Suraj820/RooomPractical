package com.example.rooompractical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rooompractical.databinding.ActivityMainBinding
import com.example.rooompractical.model.Subscribers
import kotlinx.coroutines.flow.observeOn

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val doa = SubscriberDataBase.getInstance(this).subscriberDAO
        val repository = SubscriberRepository(doa)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
        binding.viewModel = subscriberViewModel
        binding.lifecycleOwner = this
        displayUsers()

        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            }
        })
        initRecyclerView()
    }

    fun displayUsers() {
        subscriberViewModel.getSavedSubscriber().observe(this, Observer {
            Log.e("TAG", "displayUsers: $it" )
        })
    }
    private fun listItemClicked(subscriber: Subscribers){
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
    fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter { selectedItem: Subscribers ->
            listItemClicked(
                selectedItem
            )
        }
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()

    }

    private fun displaySubscribersList() {
        subscriberViewModel.getSavedSubscriber().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
}