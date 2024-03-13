package com.dafay.demo.widgets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.setMargins
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dafay.demo.lib.base.ui.base.BaseActivity
import com.dafay.demo.lib.base.utils.dp2px
import com.dafay.demo.widgets.databinding.ActivityGuideBinding
import com.google.android.material.button.MaterialButton

class GuideActivity : BaseActivity(R.layout.activity_guide) {
    override val binding: ActivityGuideBinding by viewBinding()

    override fun initViews() {
        addJumpBtns()
    }

    /**
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
        addBtn("CoordinatorLayout+Toolbar", {
            startActivity(Intent(this, BasicCoordinatorLayoutActivity::class.java))
        })
        addBtn("CoordinatorLayout+Toolbar+BottomNavigationView+Fab", {
            startActivity(Intent(this, MainActivity::class.java))
        })
    }

    private fun addBtn(text: String, func: () -> Unit) {
        val button = MaterialButton(this)
        val marginLayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        marginLayoutParams.setMargins(8.dp2px)
        binding.llContainer.addView(button, marginLayoutParams)
        button.setText(text)
        button.setOnClickListener {
            func.invoke()
        }
    }

}