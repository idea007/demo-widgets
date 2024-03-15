package com.dafay.demo.widgets.page

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseActivity
import com.dafay.demo.widgets.R
import com.dafay.demo.widgets.databinding.ActivityDrawerLayoutBinding
import com.dafay.demo.widgets.page.adapter.TestPageAdapter
import com.dafay.demo.widgets.page.bottomnav.ScrollingFragment
import com.google.android.material.navigation.NavigationBarView


class DrawerLayoutActivity : BaseActivity(R.layout.activity_drawer_layout) {
    override val binding: ActivityDrawerLayoutBinding by viewBinding()

    private val fragments = ArrayList<Fragment>()
    override fun initViews() {
        super.initViews()
        initToolbar()
        initDrawerLayout()
        initViewPager2()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initDrawerLayout() {

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // 处理导航项点击事件
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun initViewPager2() {
        fragments.apply {
            add(ScrollingFragment.newInstance(getString(R.string.color_library)))
            add(ScrollingFragment.newInstance(getString(R.string.creation)))
            add(ScrollingFragment.newInstance(getString(R.string.tools)))
        }
        binding.vpViewpager2.offscreenPageLimit = 1
        binding.vpViewpager2.setPageTransformer(MarginPageTransformer(5))
        binding.vpViewpager2.adapter = TestPageAdapter(this, fragments)

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