package com.imax.cefr.fragments.student.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.imax.cefr.databinding.ItemTestTypeBinding
import com.imax.cefr.fragments.student.home.StudentHomeFragment
import com.imax.cefr.fragments.student.home.TestTypeData


class TestTypeAdapter: ListAdapter<TestTypeData, TestTypeAdapter.TestTypeViewHolder>(object :
    DiffUtil.ItemCallback<TestTypeData>() {
    override fun areItemsTheSame(
        oldItem: TestTypeData,
        newItem: TestTypeData
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: TestTypeData,
        newItem: TestTypeData
    ) = oldItem.id == newItem.id
}) {

    private var onItemClickListener: ((TestTypeData) -> Unit)? = null

    fun setOnClickListener(block: (TestTypeData) -> Unit) {
        onItemClickListener = block
    }

    inner class TestTypeViewHolder(private val binding: ItemTestTypeBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = getItem(position)
            binding.tvTestType.text = d.testType
            binding.tvBall.text = "${d.ball}%"
            binding.progressBar.progress = d.ball

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(d)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestTypeViewHolder {
        return TestTypeViewHolder(
            ItemTestTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TestTypeViewHolder, position: Int) {
        holder.bind(position)
    }
}