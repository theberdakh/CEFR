package com.example.cefr.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cefr.data.models.LiveVideoDataClass
import com.example.cefr.databinding.ItemLanguageBinding

class ViewPagerAdapter : ListAdapter<LiveVideoDataClass, ViewPagerAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<LiveVideoDataClass>() {
        override fun areItemsTheSame(
            oldItem: LiveVideoDataClass,
            newItem: LiveVideoDataClass
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: LiveVideoDataClass,
            newItem: LiveVideoDataClass
        ) = oldItem.id == newItem.id
    }
) {

    inner class ViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position)
            binding.tvAddOne.text = "Tema ati: ${data.id}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}
