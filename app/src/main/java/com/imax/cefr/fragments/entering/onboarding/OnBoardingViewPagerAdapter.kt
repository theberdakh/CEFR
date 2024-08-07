package com.imax.cefr.fragments.entering.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.data.models.OnBoardingData
import com.imax.cefr.databinding.ItemGridImageBinding

class OnBoardingViewPagerAdapter : ListAdapter<OnBoardingData, OnBoardingViewPagerAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<OnBoardingData>() {
    override fun areItemsTheSame(oldItem: OnBoardingData, newItem: OnBoardingData) = oldItem == newItem

    override fun areContentsTheSame(oldItem: OnBoardingData, newItem: OnBoardingData) = oldItem.id == newItem.id
}) {

    inner class ViewHolder(private val binding: ItemGridImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position)
            binding.ivImage.setImageResource(data.image)
            binding.tvTitle.text = data.title
            binding.tvDescription.text = data.description
//            onItemChangedClickListener?.invoke(data)
        }
    }

    private var onItemChangedClickListener: ((OnBoardingData) -> Unit)? = null
    fun onItemChangedClickListener(block: ((OnBoardingData) -> Unit)) {
        onItemChangedClickListener = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemGridImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}
