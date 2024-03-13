package com.dafay.demo.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseActivity
import com.dafay.demo.widgets.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity(R.layout.activity_main) {
    override val binding: ActivityMainBinding by viewBinding()

    private val fragments = ArrayList<Fragment>()

    override fun initViews() {
        initViewPager2()
    }

    private fun initViewPager2() {
        fragments.apply {
            add(ScrollingFragment.newInstance("配色库"))
            add(ScrollingFragment.newInstance("创作中心"))
            add(ScrollingFragment.newInstance("工具集"))
        }
        binding.vpViewpager2.offscreenPageLimit = 1
        binding.vpViewpager2.setPageTransformer(MarginPageTransformer(5))
        binding.vpViewpager2.adapter = TestPageAdapter(this, fragments)
        binding.vpViewpager2.isUserInputEnabled = false

        binding.bnvNavigation.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.action_page_1 ->
                        binding.vpViewpager2.currentItem = 0

                    R.id.action_page_2 ->
                        binding.vpViewpager2.currentItem = 1

                    R.id.action_page_3 ->
                        binding.vpViewpager2.currentItem = 2
                }
                return true
            }
        })

        binding.vpViewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bnvNavigation.selectedItemId = R.id.action_page_1
                    1 -> binding.bnvNavigation.selectedItemId = R.id.action_page_2
                    2 -> binding.bnvNavigation.selectedItemId = R.id.action_page_3
                }
            }
        })
    }

}