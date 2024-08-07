package com.imax.cefr.fragments.teacher.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragment
import com.imax.cefr.core.base.fragment.replaceFragment
import com.imax.cefr.fragments.teacher.adapter.LiveVideDataClassListAdapter
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.databinding.FragmentTeacherMainBinding
import com.imax.cefr.fragments.teacher.adapter.PinnedLiveStreamsAdapter
import com.imax.cefr.fragments.teacher.home.TeacherHomeFragment
import com.imax.cefr.fragments.teacher.profile.TeacherProfileFragment
import com.imax.cefr.fragments.teacher.stream.LiveStreamActivity
import org.koin.android.ext.android.inject

class TeacherMainFragment : Fragment() {
    private lateinit var binding: FragmentTeacherMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeacherMainBinding.inflate(inflater, container, false)
        childFragmentManager.replaceFragment(R.id.fragment_teacher_main_container, TeacherHomeFragment())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabTeacherMain.setOnClickListener {
            val intent = Intent(requireActivity(), LiveStreamActivity::class.java)
            startActivity(intent)
        }
        binding.bottomNavTeacherMain.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.item_teacher_home_fragment -> {
                    binding.toolbar.title = "Main"
                    childFragmentManager.replaceFragment(
                        R.id.fragment_teacher_main_container,
                        TeacherHomeFragment()
                    )
                }
                R.id.item_teacher_profile_fragment -> {
                    binding.toolbar.title = "Profile"
                    childFragmentManager.replaceFragment(
                        R.id.fragment_teacher_main_container,
                        TeacherProfileFragment()
                    )
                }
                else -> UnknownError("Fragment not found for menu item: ${menuItem.title}")
            }
            true
        }


    }

   /* private lateinit var vpAdapter: PinnedLiveStreamsAdapter
    private lateinit var rvAdapter: LiveVideDataClassListAdapter
    private val localStorage: LocalStorage by inject()*/


    /*  vpAdapter = PinnedLiveStreamsAdapter()
       rvAdapter = LiveVideDataClassListAdapter()

       binding.viewPager.adapter = vpAdapter
       val list = mutableListOf<LiveVideoDataClass>()
       repeat(10) {
           list.add(
               LiveVideoDataClass(
                   it
               )
           )
       }
       vpAdapter.submitList(list)

       val list2 = mutableListOf<LiveVideoDataClass>()
       repeat(5) {
           list2.add(LiveVideoDataClass(it))
       }
       rvAdapter.submitList(list2)

       if (localStorage.channelName == "amir_b1") {
           binding.cardInnerOne.visibility = View.GONE
       } else if (localStorage.channelName == "user_nukus") {
           binding.cardInnerTwo.visibility = View.GONE
       } else if (localStorage.channelName == "user_xojeli") {
           binding.cardInnerThree.visibility = View.GONE
       } else if (localStorage.channelName == "user_shomanay") {
           binding.cardInnerFour.visibility = View.GONE
       } else if (localStorage.channelName == "user_kegeyli") {
           binding.cardInnerFive.visibility = View.GONE
       }

       binding.cardInnerOne.setOnClickListener {
           *//*   findNavController().navigate(
                   MainFragmentDirections.actionMainFragmentToWebViewFragment("amir_b1")
               )*//*
        }

        binding.cardInnerTwo.setOnClickListener {
            *//* findNavController().navigate(
                 MainFragmentDirections.actionMainFragmentToWebViewFragment("user_nukus")
             )*//*
        }

        binding.cardInnerThree.setOnClickListener {
            *//* findNavController().navigate(
                 MainFragmentDirections.actionMainFragmentToWebViewFragment("user_xojeli")
             )*//*
        }

        binding.cardInnerFour.setOnClickListener {
            *//*  findNavController().navigate(
                  MainFragmentDirections.actionMainFragmentToWebViewFragment("user_shomanay")
              )*//*
        }

        binding.cardInnerFive.setOnClickListener {
            *//* findNavController().navigate(
                 MainFragmentDirections.actionMainFragmentToWebViewFragment("user_kegeyli")
             )*//*
        }*/
    override fun onDestroyView() {
        super.onDestroyView()
    }

}
