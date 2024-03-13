package com.dafay.demo.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseFragment
import com.dafay.demo.widgets.databinding.FragmentScrollingBinding

class ScrollingFragment : BaseFragment {
    override val binding: FragmentScrollingBinding by viewBinding()

    private var passTitle: String? = null

    private constructor() : super(R.layout.fragment_scrolling)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            passTitle = it.getString("title")
        }
    }

    override fun initViews() {
        super.initViews()
        binding.tvTitle.text="页面："+passTitle
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ScrollingFragment().apply {
                arguments = Bundle().apply {
                    putString("title", param1)
                }
            }
    }

}