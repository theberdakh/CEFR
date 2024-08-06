package com.example.cefr.fragments.student.rating

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cefr.data.models.RatingData
import com.example.cefr.databinding.ItemTopLeadersBinding

class RatingAdapter: ListAdapter<RatingData, RatingAdapter.RatingViewHolder >(object :
    DiffUtil.ItemCallback<RatingData>() {
    override fun areItemsTheSame(
        oldItem: RatingData,
        newItem: RatingData
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: RatingData,
        newItem: RatingData
    ) = oldItem.id == newItem.id
}) {

    inner class RatingViewHolder(private val binding: ItemTopLeadersBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)
            binding.tvName.text = d.name
            binding.tvTime.text = d.time
            binding.tvScience.text = d.science
            binding.profileImage.setImageResource(d.profileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        return RatingViewHolder(
            ItemTopLeadersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bind(position)
    }
}