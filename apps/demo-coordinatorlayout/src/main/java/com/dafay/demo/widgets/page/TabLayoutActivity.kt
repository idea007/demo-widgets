package com.dafay.demo.widgets.page

import android.graphics.Color
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.MarginPageTransformer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseActivity
import com.dafay.demo.widgets.R
import com.dafay.demo.widgets.databinding.ActivityTabLayoutBinding
import com.dafay.demo.widgets.page.adapter.TestPageAdapter
import com.dafay.demo.widgets.page.bottomnav.ScrollingFragment
import com.google.android.material.tabs.TabLayout

class TabLayoutActivity : BaseActivity(R.layout.activity_tab_layout) {
    override val binding: ActivityTabLayoutBinding by viewBinding()


    private val tabs = ArrayList<String>().apply {
        add("Tab1")
        add("Tab2")
        add("Tab3")
        add("Long Tab4")
        add("Tab4")
    }

    private val fragments = ArrayList<Fragment>()

    override fun initViews() {
        super.initViews()
        initViewpager2()
    }


    private fun initViewpager2() {
        tabs.forEach {
            fragments.add(ScrollingFragment.newInstance(it))
        }
        binding.vpViewpager2.offscreenPageLimit = 1
        binding.vpViewpager2.setPageTransformer(MarginPageTransformer(5))
        binding.vpViewpager2.adapter = TestPageAdapter(this, fragments)
        binding.vpViewpager2.isUserInputEnabled = false
        binding.tlTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.customView?.apply {
                    this.findViewById<TextView>(R.id.tv_text).setBackgroundColor(Color.parseColor("#00ffaa"))
                }
                binding.vpViewpager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView?.apply {
                    this.findViewById<TextView>(R.id.tv_text).setBackgroundColor(Color.parseColor("#80aaff00"))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        tabs.forEach {
            val tab = binding.tlTablayout.newTab()
            tab.setCustomView(R.layout.item_custom_tablayout)
            tab.customView?.apply {
                this.findViewById<TextView>(R.id.tv_text).text = it
            }
            binding.tlTablayout.addTab(tab)
        }
    }

}