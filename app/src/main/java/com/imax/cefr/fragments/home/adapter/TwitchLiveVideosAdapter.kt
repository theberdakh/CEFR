package com.imax.cefr.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.R
import com.imax.cefr.data.models.twitch.live.TwitchLiveDataChild
import com.imax.cefr.databinding.ItemScheduledLiveStreamBinding
import kotlin.math.abs

class TwitchLiveDataChildItemCallback: DiffUtil.ItemCallback<TwitchLiveDataChild>(){
    override fun areItemsTheSame(
        oldItem: TwitchLiveDataChild,
        newItem: TwitchLiveDataChild
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TwitchLiveDataChild,
        newItem: TwitchLiveDataChild
    ): Boolean {
        return oldItem.id == newItem.id && oldItem.started_at == newItem.started_at
    }

}

class TwitchLiveVideosAdapter:
    ListAdapter<TwitchLiveDataChild, TwitchLiveVideosAdapter.ViewHolder>(TwitchLiveDataChildItemCallback()) {

    inner class ViewHolder(private val binding: ItemScheduledLiveStreamBinding): RecyclerView.ViewHolder(binding.root){
        fun bind() {
            val live = getItem(absoluteAdapterPosition)
            binding.streamTheme.text = live.user_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemScheduledLiveStreamBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_scheduled_live_stream, parent, false)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind()
}
