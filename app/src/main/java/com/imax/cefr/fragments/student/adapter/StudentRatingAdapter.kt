package com.imax.cefr.fragments.student.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.imax.cefr.data.models.RatingData
import com.imax.cefr.databinding.ItemTopLeadersBinding

class StudentRatingAdapter: ListAdapter<RatingData, StudentRatingAdapter.StudentRatingViewHolder>(object :
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

    inner class StudentRatingViewHolder(private val binding: ItemTopLeadersBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)
            binding.tvName.text = d.name
            binding.tvTime.text = d.time
            binding.tvScience.text = d.science
            binding.profileImage.setImageResource(d.profileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRatingViewHolder {
        return StudentRatingViewHolder(
            ItemTopLeadersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentRatingViewHolder, position: Int) {
        holder.bind(position)
    }
}