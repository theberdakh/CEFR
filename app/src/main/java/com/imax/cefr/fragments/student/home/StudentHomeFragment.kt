package com.imax.cefr.fragments.student.home

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.databinding.FragmentStudentHomeBinding
import com.imax.cefr.fragments.student.adapter.TestTypeAdapter

data class TestTypeData(
    val id: Int,
    val testType: String,
    val ball: Int
)

class StudentHomeFragment :
    BaseFragment<FragmentStudentHomeBinding>(FragmentStudentHomeBinding::inflate) {


    private val adapter by lazy(LazyThreadSafetyMode.NONE) {  TestTypeAdapter() }

    override fun FragmentStudentHomeBinding.observeViewModel() {}

    override fun FragmentStudentHomeBinding.setUpViews() {


        recyclerView.adapter = adapter

        val testTypes = mutableListOf<TestTypeData>()
        testTypes.add(TestTypeData(0, "Grammar", 73))
        testTypes.add(TestTypeData(1, "Listening", 30))
        testTypes.add(TestTypeData(2, "Reading", 46))
        testTypes.add(TestTypeData(3, "Speaking", 67))
        testTypes.add(TestTypeData(4, "Writing", 86))

        adapter.submitList(testTypes)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.activity_container_view) as NavHostFragment
        val navController = navHostFragment.findNavController()

        adapter.setOnClickListener {
            when (it.id) {
                0 -> navController.navigate(R.id.action_student_main_fragment_to_grammarFragment)
                1 -> navController.navigate(R.id.action_student_main_fragment_to_listeningFragment)
                2 -> navController.navigate(R.id.action_student_main_fragment_to_readingFragment)
                3 -> navController.navigate(R.id.action_student_main_fragment_to_speakingFragment)
                4 -> navController.navigate(R.id.action_student_main_fragment_to_writingFragment)
            }
        }

    }

    override fun FragmentStudentHomeBinding.navigation() {}


}
