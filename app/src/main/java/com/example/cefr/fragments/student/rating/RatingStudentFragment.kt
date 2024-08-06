package com.example.cefr.fragments.student.rating

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cefr.R
import com.example.cefr.RVAdapter
import com.example.cefr.data.models.RatingData
import com.example.cefr.databinding.FragmentRatingStudentBinding

class RatingStudentFragment: Fragment(R.layout.fragment_rating_student), View.OnClickListener  {
    private var _binding: FragmentRatingStudentBinding? = null
    private val binding: FragmentRatingStudentBinding get() = _binding!!

    private lateinit var def: ColorStateList
    private lateinit var item1: TextView
    private lateinit var item2: TextView
    private lateinit var item3: TextView
    private lateinit var select: TextView

    private val adapter = RatingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRatingStudentBinding.bind(view)

        item1 = requireActivity().findViewById(R.id.item1)
        item2 = requireActivity().findViewById(R.id.item2)
        item3 = requireActivity().findViewById(R.id.item3)

        item1.setOnClickListener(this)
        item2.setOnClickListener(this)
        item3.setOnClickListener(this)

        select = requireActivity().findViewById(R.id.select)
        def = item2.textColors

        val languages = resources.getStringArray(R.array.rating_types)
        val arrayAdapter = ArrayAdapter(requireActivity(), R.layout.item_dropdown, languages)
        val autocompleteTV = requireActivity().findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)


        binding.recyclerView.adapter = adapter
        val leaders = mutableListOf<RatingData>()
        repeat(20) {
            leaders.add(RatingData(it, "Alex", "Fizika", it, R.drawable.ellipse, "20 August"))
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
}
