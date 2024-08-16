package com.imax.cefr.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.ItemScheduledLiveStreamBinding



class StreamResponseDataItemCallback: DiffUtil.ItemCallback<StreamResponseData>(){
    override fun areItemsTheSame(
        oldItem: StreamResponseData,
        newItem: StreamResponseData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: StreamResponseData,
        newItem: StreamResponseData
    ): Boolean {
        return oldItem.id == newItem.id && oldItem.teacherId == newItem.teacherId
    }
}


class StreamRecyclerAdapter: ListAdapter<StreamResponseData, StreamRecyclerAdapter.StreamViewHolder>((StreamResponseDataItemCallback())) {

    inner class StreamViewHolder(private val binding: ItemScheduledLiveStreamBinding): ViewHolder(binding.root){
        fun bind(){
            val stream = getItem(absoluteAdapterPosition)
            binding.streamTheme.text = stream.streamTitle
            binding.streamAuthor.text = stream.teacherName
            binding.streamAuthorSubtitle.text = "Teacher"
            binding.streamTime.text = stream.startTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamViewHolder {
        return StreamViewHolder(ItemScheduledLiveStreamBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: StreamViewHolder, position: Int)  = holder.bind()


}
