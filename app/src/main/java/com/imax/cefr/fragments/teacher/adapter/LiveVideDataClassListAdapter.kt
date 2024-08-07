package com.imax.cefr.fragments.teacher.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.databinding.ItemPreviousBinding

class LiveVideDataClassListAdapter : ListAdapter<LiveVideoDataClass, LiveVideDataClassListAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<LiveVideoDataClass>() {
    override fun areItemsTheSame(
        oldItem: LiveVideoDataClass,
        newItem: LiveVideoDataClass
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: LiveVideoDataClass,
        newItem: LiveVideoDataClass
    ) = oldItem.id == newItem.id
}) {

    inner class ViewHolder(private val binding: ItemPreviousBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position)
            binding.tvOne.text = "Teacher: ${1 + data.id}"
            binding.tvTwo.text = "Subject: ${data.id}"
            binding.tvThree.text = "Time: ${data.id}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPreviousBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}
