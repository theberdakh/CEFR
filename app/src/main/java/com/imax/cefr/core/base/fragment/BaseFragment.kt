package com.imax.cefr.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<VB> =(LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VB: ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    var binding: VB? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            observeViewModel()
            setUpViews()
            navigation()
        }
    }

    abstract fun VB.navigation()

    abstract fun VB.setUpViews()

    abstract fun VB.observeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    protected fun showInputEditText(etView: EditText) {
        etView.requestFocus()
        val inputMethod =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethod?.showSoftInput(etView, InputMethodManager.SHOW_IMPLICIT)
    }

    protected fun hideInputEditText(etView: EditText) {
        etView.clearFocus()
        val inputMethod =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethod?.hideSoftInputFromWindow(etView.windowToken, 0)
    }

}
