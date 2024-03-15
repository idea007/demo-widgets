package com.dafay.demo.widgets.page.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @ClassName RecordPageAdapter
 * @Des 烹饪记录 vp2 adapter
 * @Author lipengfei
 * @Date 2023/11/20 13:40
 */
class TestPageAdapter(activity: FragmentActivity, val fragments: List<Fragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(position)
    }
}