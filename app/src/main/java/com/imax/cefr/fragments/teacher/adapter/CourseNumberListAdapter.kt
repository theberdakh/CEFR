package com.imax.cefr.fragments.teacher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imax.cefr.R
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

        private var selectedPosition = RecyclerView.NO_POSITION

    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickListener(block: ((Int) -> Unit)) {
        onClickListener = block
    }

    private var onClickListenerExtended: ((Int, View) -> Unit)? = null
    fun setOnClickListenerExtended(block: ((Int, View) -> Unit)) {
        onClickListenerExtended = block
    }

    inner class ViewHolder(private val binding: ItemCourseNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {

                if (position == selectedPosition){
                    root.setBackgroundResource(R.drawable.shape_card_rectangular_stroke)}
                else {
                    root.setBackgroundResource(R.drawable.shape_card_rectangular_no_stroke)
                }

                getItem(absoluteAdapterPosition)?.let { courseNumber ->
                    root.text = courseNumber.number.toString()
                    root.setOnClickListener {
                        onClickListenerExtended?.invoke(courseNumber.number, root)
                        onClickListener?.invoke(courseNumber.number)

                        notifyItemChanged(selectedPosition) // Clear the old selection
                        selectedPosition = absoluteAdapterPosition
                        notifyItemChanged(selectedPosition)
                    }
                }


            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

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
