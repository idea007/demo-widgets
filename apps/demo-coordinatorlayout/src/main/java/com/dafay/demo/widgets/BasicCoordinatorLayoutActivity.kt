package com.dafay.demo.widgets

import android.view.Menu
import android.view.MenuItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseActivity
import com.dafay.demo.widgets.databinding.ActivityBasicCoordinatorLayoutBinding
import com.google.android.material.appbar.AppBarLayout


class BasicCoordinatorLayoutActivity : BaseActivity(R.layout.activity_basic_coordinator_layout) {
    override val binding: ActivityBasicCoordinatorLayoutBinding by viewBinding()


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_item1 -> {
                setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL)
            }

            R.id.action_item2 -> {
                setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
     */
    private fun setScrollFlags(flags: Int) {
        val layoutParams = binding.toolbar.layoutParams as AppBarLayout.LayoutParams
        layoutParams.scrollFlags = flags
        binding.toolbar.layoutParams = layoutParams

    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(binding.toolbar)

    }

}