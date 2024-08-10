package com.imax.cefr.fragments.teacher.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.databinding.ItemCourseNumberBinding

data class CourseNumber(
    val number: Int
)

class CourseNumberDiffCallback : DiffUtil.ItemCallback<CourseNumber>() {
    override fun areItemsTheSame(oldItem: CourseNumber, newItem: CourseNumber): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: CourseNumber, newItem: CourseNumber): Boolean {
        return oldItem.number == newItem.number
    }
}

class CourseNumberListAdapter :
    ListAdapter<CourseNumber, CourseNumberListAdapter.ViewHolder>(CourseNumberDiffCallback()) {

    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickListener(block: ((Int) -> Unit)) {
        onClickListener = block
    }

    inner class ViewHolder(private val binding: ItemCourseNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                getItem(absoluteAdapterPosition)?.let { courseNumber ->
                    root.text = courseNumber.number.toString()
                    root.setOnClickListener {
                        onClickListener?.invoke(courseNumber.number)
                    }
                }


            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCourseNumberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


}
