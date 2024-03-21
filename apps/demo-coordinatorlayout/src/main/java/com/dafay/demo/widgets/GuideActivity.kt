package com.dafay.demo.widgets

import android.content.Intent
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseActivity
import com.dafay.demo.lib.base.utils.dp2px
import com.dafay.demo.widgets.databinding.ActivityGuideBinding
import com.dafay.demo.widgets.page.BasicCoordinatorLayoutActivity
import com.dafay.demo.widgets.page.bottomnav.BottomNavigationBehaviorActivity
import com.dafay.demo.widgets.page.CollapsingToolbarActivity
import com.dafay.demo.widgets.page.DrawerLayoutActivity
import com.dafay.demo.widgets.page.TabLayoutActivity
import com.google.android.material.textview.MaterialTextView

class GuideActivity : BaseActivity(R.layout.activity_guide) {
    override val binding: ActivityGuideBinding by viewBinding()

    override fun initViews() {
        addJumpBtns()
    }

    /**
     * 推荐命名
     * 基本使用: BasicCoordinatorLayoutActivity
     * 滚动行为: ScrollingBehaviorActivity
     * 折叠工具栏: CollapsingToolbarActivity
     * 浮动操作按钮: FloatingActionButtonActivity
     * 折叠工具栏与图片: CollapsingToolbarWithImageActivity
     * 滚动隐藏工具栏: ScrollHideToolbarActivity
     * 与列表视图结合: CoordinatorWithListViewActivity
     * 与网格视图结合: CoordinatorWithGridViewActivity
     * 与底部导航结合: BottomNavigationBehaviorActivity
     * 滑动交互响应: SwipeBehaviorActivity
     * 自定义行为: CustomBehaviorActivity
     * 与滑动选项卡结合: TabbedNavigationActivity
     * 粘性头部效果: StickyHeaderEffectActivity
     * 进入总是折叠: EnterAlwaysCollapsedActivity
     * 退出直到折叠: ExitUntilCollapsedActivity
     */
    private fun addJumpBtns() {
        addBtn("CoordinatorLayout+AppBarLayout+Toolbar", {
            startActivity(Intent(this, BasicCoordinatorLayoutActivity::class.java))
        })

        addBtn("CoordinatorLayout+AppBarLayout+CollapsingToolbarLayout+Toolbar", {
            startActivity(Intent(this, CollapsingToolbarActivity::class.java))
        })

        addBtn("CoordinatorLayout+Toolbar+BottomNavigationView+Fab", {
            startActivity(Intent(this, BottomNavigationBehaviorActivity::class.java))
        })

        addBtn("TabLayout+ViewPager2", {
            startActivity(Intent(this, TabLayoutActivity::class.java))
        })

        addBtn("DrawerLayout+CoordinatorLayout", {
            startActivity(Intent(this, DrawerLayoutActivity::class.java))
        })
    }

    private fun addBtn(text: String, func: () -> Unit) {
        val textview = MaterialTextView(this)
        val marginLayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        marginLayoutParams.setMargins(8.dp2px)
        textview.setPadding(8.dp2px)
        binding.llContainer.addView(textview, marginLayoutParams)
        textview.setBackgroundColor(Color.CYAN)
        textview.setText(text)
        textview.setOnClickListener {
            func.invoke()
        }
    }

}