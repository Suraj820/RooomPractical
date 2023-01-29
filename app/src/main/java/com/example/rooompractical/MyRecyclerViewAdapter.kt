package com.example.rooompractical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rooompractical.databinding.ListItemBinding
import com.example.rooompractical.model.Subscribers


class MyRecyclerViewAdapter(private val clickListener: (Subscribers)-> Unit ):
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    private val subscribersList = ArrayList<Subscribers>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return  subscribersList.size
    }

    fun setList(subscribers: List<Subscribers>) {
        subscribersList.clear()
        subscribersList.addAll(subscribers)

    }
    class MyViewHolder(val binding : ListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(subscriber: Subscribers,clickListener: (Subscribers) -> Unit){
            binding.nameTextView.text = subscriber.name
            binding.emailTextView.text = subscriber.email
            binding.listItemLayout.setOnClickListener {
                clickListener(subscriber)
            }
        }
    }
}