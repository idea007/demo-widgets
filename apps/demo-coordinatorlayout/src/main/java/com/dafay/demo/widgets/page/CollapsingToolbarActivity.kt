package com.dafay.demo.widgets.page

import android.view.Menu
import android.view.MenuItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseActivity
import com.dafay.demo.widgets.R
import com.dafay.demo.widgets.databinding.ActivityCollapsingToolbarBinding
import com.google.android.material.appbar.AppBarLayout

class CollapsingToolbarActivity : BaseActivity(R.layout.activity_collapsing_toolbar) {
    override val binding: ActivityCollapsingToolbarBinding by viewBinding()


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_item1 -> {
                setScrollFlags(item.title.toString(), AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL)
            }

            R.id.action_item2 -> {
                setScrollFlags(
                    item.title.toString(),
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                )
            }

            R.id.action_item3 -> {
                setScrollFlags(
                    item.title.toString(),
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED,
                )
            }

            R.id.action_item4 -> {
                setScrollFlags(
                    item.title.toString(),
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                )
            }

            R.id.action_item5 -> {
                setScrollFlags(
                    item.title.toString(),
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                )
            }

            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
     */
    private fun setScrollFlags(subtitle: String?, flags: Int) {
        val layoutParams = binding.collapsingToolbar.layoutParams as AppBarLayout.LayoutParams
        layoutParams.scrollFlags = flags
        binding.collapsingToolbar.layoutParams = layoutParams
        binding.tvMode.text = subtitle
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //添加默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true) //设置返回键可用
        setScrollFlags("scroll", AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL)
        binding.collapsingToolbar.setTitle("CollapsingToolbar");
    }

}