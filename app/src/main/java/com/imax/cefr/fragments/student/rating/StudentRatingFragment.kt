package com.imax.cefr.fragments.student.rating

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.data.models.RatingData
import com.imax.cefr.databinding.FragmentStudentRatingBinding
import com.imax.cefr.fragments.student.adapter.StudentRatingAdapter

class StudentRatingFragment: Fragment(R.layout.fragment_student_rating), View.OnClickListener {
    private var _binding: FragmentStudentRatingBinding? = null
    private val binding: FragmentStudentRatingBinding get() = _binding!!

    private lateinit var def: ColorStateList
    private lateinit var item1: TextView
    private lateinit var item2: TextView
    private lateinit var item3: TextView
    private lateinit var select: TextView

    private val adapter = StudentRatingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStudentRatingBinding.bind(view)

        // Spinner setup
        val spinnerItems = listOf("Grammar", "Listening", "Reading", "Speaking", "Writing")
        val adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, spinnerItems)
        binding.autoCompleteTextView.setAdapter(adapterSpinner)

        // Other view initializations
        item1 = binding.root.findViewById(R.id.item1)
        item2 = binding.root.findViewById(R.id.item2)
        item3 = binding.root.findViewById(R.id.item3)

        item1.setOnClickListener(this)
        item2.setOnClickListener(this)
        item3.setOnClickListener(this)

        select = binding.root.findViewById(R.id.select)
        def = item2.textColors


        binding.recyclerView.adapter = adapter

        val leaders = mutableListOf<RatingData>()
        repeat(20) {
            leaders.add(RatingData(it, "Damir", "Matematika", 1, "12:00", R.drawable.ellipse))
        }
        adapter.submitList(leaders)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.item1 -> {
                select.animate().x(0f).duration = 100
                item1.setTextColor(Color.WHITE)
                item2.setTextColor(def)
                item3.setTextColor(def)
            }
            R.id.item2 -> {
                item1.setTextColor(def)
                item2.setTextColor(Color.WHITE)
                item3.setTextColor(def)
                val size = item2.width
                select.animate().x(size.toFloat()).duration = 100
            }
            R.id.item3 -> {
                item1.setTextColor(def)
                item3.setTextColor(Color.WHITE)
                item2.setTextColor(def)
                val size = item2.width * 2
                select.animate().x(size.toFloat()).duration = 100
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
