package com.imax.cefr.fragments.teacher.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.R
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.ItemScheduledLiveStreamBinding


class AllStreamsPagingAdapter :
    PagingDataAdapter<StreamResponseData, AllStreamsPagingAdapter.ViewHolder>(
        StreamResponseDataItemCallback()
    ) {
    inner class ViewHolder(private val binding: ItemScheduledLiveStreamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
           getItem(absoluteAdapterPosition)?.apply {
                binding.streamTheme.text = streamTitle
                binding.streamAuthor.text = teacherName
                binding.streamAuthorSubtitle.text = "Teacher"
                binding.streamTime.text = startTime
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemScheduledLiveStreamBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_scheduled_live_stream, parent, false)
            )
        )
    }
}
