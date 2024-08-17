package com.imax.cefr.fragments.channel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imax.cefr.R
import com.imax.cefr.data.models.twitch.TwitchVideoDataChild
import com.imax.cefr.databinding.ItemScheduledLiveStreamBinding

class TwitchVideoDataChildCallback : DiffUtil.ItemCallback<TwitchVideoDataChild>() {
    override fun areItemsTheSame(
        oldItem: TwitchVideoDataChild,
        newItem: TwitchVideoDataChild
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TwitchVideoDataChild,
        newItem: TwitchVideoDataChild
    ): Boolean {
        return oldItem.id == newItem.id && oldItem.stream_id == newItem.stream_id
    }

}

class TwitchVideosAdapter : ListAdapter<TwitchVideoDataChild, TwitchVideosAdapter.ViewHolder>
    (TwitchVideoDataChildCallback()) {

    inner class ViewHolder(private val binding: ItemScheduledLiveStreamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.streamTheme.text = item.title
            binding.streamDate.text = item.created_at
            Log.d("TwitchVideosAdapter", item.thumbnail_url)

            val updatedWidth = binding.ivPicture1.width
            val updatedHeight = binding.ivPicture1.height

            val updatedImageUrl =
                item.thumbnail_url.replace("%{width}", "250")
                    .replace("%{height}", "400")

            binding.ivPicture1.load(updatedImageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_soon_background)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemScheduledLiveStreamBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_scheduled_live_stream, parent, false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

}
