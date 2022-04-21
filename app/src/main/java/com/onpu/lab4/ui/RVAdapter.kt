package com.onpu.lab4.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onpu.lab4.R
import com.onpu.lab4.databinding.ItemBinding
import com.onpu.lab4.model.ItemModel

class RVAdapter(private val callback: (ItemModel) -> Unit) :
    ListAdapter<ItemModel, RVViewHolder>(Diffs()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)

        return RVViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class RVViewHolder(itemview: View, private val callback: (ItemModel) -> Unit) :
    RecyclerView.ViewHolder(itemview) {

    private val binding = ItemBinding.bind(itemView)


    fun bind(itemModel: ItemModel) {
        binding.apply {
            tvName.text = itemModel.name
            Glide.with(iv)
                .load(itemModel.icon)
                //.transition(withCrossFade(DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(iv)
            tvValue.text = itemModel.price.toString()
            tvAvg.text = itemModel.avg7daysPrice.toString()
        }
        binding.containerItem.setOnClickListener {
            callback(itemModel)
        }
    }
}

class Diffs : DiffUtil.ItemCallback<ItemModel>() {
    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem.uid == newItem.uid
    }

}