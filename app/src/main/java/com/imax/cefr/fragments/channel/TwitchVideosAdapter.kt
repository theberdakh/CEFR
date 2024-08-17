package com.imax.cefr.fragments.channel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imax.cefr.R
import com.imax.cefr.data.models.twitch.video.TwitchVideoDataChild
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

    private var onVideoClick: ((TwitchVideoDataChild) -> Unit)? = null
    fun setOnVideoClickListener(block: (TwitchVideoDataChild) -> Unit) {
        onVideoClick = block
    }

    inner class ViewHolder(private val binding: ItemScheduledLiveStreamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.streamTheme.text = item.title
            binding.streamDate.text = item.created_at
            Log.d("TwitchVideosAdapter", item.thumbnail_url)

            binding.cardView.setOnClickListener {
                onVideoClick?.invoke(item)
            }

            val updatedImageUrl =
                item.thumbnail_url.replace("%{width}", "250")
                    .replace("%{height}", "400")

            Glide.with(binding.root)
                .load(updatedImageUrl)
                .placeholder(R.drawable.ic_soon_background)
                .into(binding.ivPicture1)

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
