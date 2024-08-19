package com.imax.cefr.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.R
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.ItemChannelBinding


class ChannelsListAdapter:
    ListAdapter<StreamResponseData, ChannelsListAdapter.ViewHolder>(StreamResponseDataItemCallback()) {

    private var onClickChannel: ((StreamResponseData) -> Unit)? = null

    fun setOnClickChannelListener(block: (StreamResponseData) -> Unit) {
        onClickChannel = block
    }

    inner class ViewHolder(private val binding: ItemChannelBinding): RecyclerView.ViewHolder(binding.root){
        fun bind() {
            val stream = getItem(absoluteAdapterPosition)
            binding.channelTitle.text = stream.teacherName
            binding.channelSubtitle.text = stream.teacherId
            binding.channelAvatarPic.setImageResource(R.drawable.avatar)
            binding.channelLayout.setOnClickListener {
                onClickChannel?.invoke(stream)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChannelBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind()
}
