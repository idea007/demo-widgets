package com.dafay.demo.widgets.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.dafay.demo.lib.base.utils.dp2px
import com.dafay.demo.widgets.R
import com.google.android.material.animation.AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
import com.google.android.material.animation.AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.motion.MotionUtils

/**
 * @Des
 * @Author lipengfei
 * @Date 2024/2/18
 */
open class ExtendedFAB @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ExtendedFloatingActionButton(context, attributeSet) {

    private val fabBehavior = ExtendedFABBehavior(context, attributeSet)

    override fun getBehavior(): CoordinatorLayout.Behavior<ExtendedFloatingActionButton> {
        return fabBehavior
    }

    /**
     * 自定义 Behavior
     */
    protected class ExtendedFABBehavior(
        context: Context,
        attributeSet: AttributeSet?
    ) : ExtendedFloatingActionButtonBehavior<ExtendedFloatingActionButton>(context, attributeSet) {

        private val DEFAULT_ENTER_ANIMATION_DURATION_MS = 225
        private val DEFAULT_EXIT_ANIMATION_DURATION_MS = 175
        private val ENTER_ANIM_DURATION_ATTR: Int = com.google.android.material.R.attr.motionDurationLong2
        private val EXIT_ANIM_DURATION_ATTR: Int = com.google.android.material.R.attr.motionDurationMedium4
        private val ENTER_EXIT_ANIM_EASING_ATTR: Int = com.google.android.material.R.attr.motionEasingEmphasizedInterpolator

        /**
         * 因为实现了 Behavior 接口，CoordinatorLayout 始终会调用该视图的 onLayoutChild。
         * 在这里自定义或修改布局，以代替默认的子布局行为。
         */
        @SuppressLint("RestrictedApi")
        override fun onLayoutChild(parent: CoordinatorLayout, child: ExtendedFloatingActionButton, layoutDirection: Int): Boolean {
            val paramsCompat = child.layoutParams as ViewGroup.MarginLayoutParams
            // height = child.measuredHeight + paramsCompat.bottomMargin
            height = 48.dp2px // 演示用
            enterAnimDuration = MotionUtils.resolveThemeDuration(
                child.context,
                ENTER_ANIM_DURATION_ATTR,
                DEFAULT_ENTER_ANIMATION_DURATION_MS
            )
            exitAnimDuration = MotionUtils.resolveThemeDuration(
                child.context,
                EXIT_ANIM_DURATION_ATTR,
                DEFAULT_EXIT_ANIMATION_DURATION_MS
            )
            enterAnimInterpolator = MotionUtils.resolveThemeInterpolator(
                child.context,
                ENTER_EXIT_ANIM_EASING_ATTR,
                LINEAR_OUT_SLOW_IN_INTERPOLATOR
            )
            exitAnimInterpolator = MotionUtils.resolveThemeInterpolator(
                child.context,
                ENTER_EXIT_ANIM_EASING_ATTR,
                FAST_OUT_LINEAR_IN_INTERPOLATOR
            )
            return super.onLayoutChild(parent, child, layoutDirection)
        }

        /**
         * 当依赖的视图发生变化时调用，可以在这里处理子视图的位置或大小变化。
         */
        override fun onDependentViewChanged(parent: CoordinatorLayout, child: ExtendedFloatingActionButton, dependency: View): Boolean {
            return super.onDependentViewChanged(parent, child, dependency)
        }

        /**
         * 用于确定一个视图是否依赖于另一个视图，返回true表示依赖，false表示不依赖。
         */
        override fun layoutDependsOn(parent: CoordinatorLayout, child: ExtendedFloatingActionButton, dependency: View): Boolean {
            return super.layoutDependsOn(parent, child, dependency)
        }

        /**
         * 用于处理滚动事件，可以在这里响应滚动事件并调整子视图的位置或大小。
         */
        override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: ExtendedFloatingActionButton,
            directTargetChild: View,
            target: View,
            axes: Int,
            type: Int
        ): Boolean {
            return axes == ViewCompat.SCROLL_AXIS_VERTICAL
        }

        /**
         * 用于处理滚动事件，可以在这里响应滚动事件并调整子视图的位置或大小。
         */
        override fun onNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: ExtendedFloatingActionButton,
            target: View,
            dxConsumed: Int,
            dyConsumed: Int,
            dxUnconsumed: Int,
            dyUnconsumed: Int,
            type: Int,
            consumed: IntArray
        ) {
            if (dyConsumed > 0) {
                shrink(child)
                slideDown(child)
            } else {
                extend(child)
                if (dyConsumed < 0) {
                    slideUp(child)
                }
            }
        }

        private fun extend(child: ExtendedFloatingActionButton) {
            child.extend()
        }

        private fun shrink(child: ExtendedFloatingActionButton) {
            child.shrink()
        }

        @HideBottomViewOnScrollBehavior.ScrollState
        private var currentState = HideBottomViewOnScrollBehavior.STATE_SCROLLED_UP
        private var currentAnimator: ViewPropertyAnimator? = null
        private val onScrollStateChangedListeners = LinkedHashSet<HideBottomViewOnScrollBehavior.OnScrollStateChangedListener>()
        private var height = 0
        private var additionalHiddenOffsetY = 0
        private var enterAnimDuration = 0
        private var exitAnimDuration = 0
        private var enterAnimInterpolator: TimeInterpolator? = null
        private lateinit var exitAnimInterpolator: TimeInterpolator

        fun isScrolledDown(): Boolean {
            return currentState == HideBottomViewOnScrollBehavior.STATE_SCROLLED_DOWN
        }

        fun slideDown(child: View) {
            slideDown(child,  /*animate=*/true)
        }

        /**
         * Slides the child with or without animation from its current position to be totally off the
         * screen.
         * @param animate `true` to slide with animation.
         */
        fun slideDown(child: View, animate: Boolean) {
            if (isScrolledDown()) {
                return
            }
            if (currentAnimator != null) {
                currentAnimator?.cancel()
                child.clearAnimation()
            }
            updateCurrentState(child, HideBottomViewOnScrollBehavior.STATE_SCROLLED_DOWN)
            val targetTranslationY: Int = height + additionalHiddenOffsetY
            if (animate) {
                animateChildTo(child, targetTranslationY, exitAnimDuration.toLong(), exitAnimInterpolator)
            } else {
                child.setTranslationY(targetTranslationY.toFloat())
            }
        }

        private fun updateCurrentState(child: View, @HideBottomViewOnScrollBehavior.ScrollState state: Int) {
            currentState = state
            for (listener in onScrollStateChangedListeners) {
                listener.onStateChanged(child, currentState)
            }
        }

        private fun animateChildTo(
            child: View, targetY: Int, duration: Long, interpolator: TimeInterpolator
        ) {
            currentAnimator = child
                .animate()
                .translationY(targetY.toFloat())
                .setInterpolator(interpolator)
                .setDuration(duration)
                .setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            currentAnimator = null
                        }
                    })
        }

        fun slideUp(child: View) {
            slideUp(child,  /*animate=*/true)
        }

        /** Returns true if the current state is scrolled up.  */
        fun isScrolledUp(): Boolean {
            return currentState == HideBottomViewOnScrollBehavior.STATE_SCROLLED_UP
        }

        /**
         * Slides the child with or without animation from its current position to be totally on the
         * screen.
         *
         * @param animate `true` to slide with animation.
         */
        fun slideUp(child: View, animate: Boolean) {
            if (isScrolledUp()) {
                return
            }
            if (currentAnimator != null) {
                currentAnimator!!.cancel()
                child.clearAnimation()
            }
            updateCurrentState(child, HideBottomViewOnScrollBehavior.STATE_SCROLLED_UP)
            val targetTranslationY = 0
            if (animate) {
                animateChildTo(child, targetTranslationY, enterAnimDuration.toLong(), enterAnimInterpolator!!)
            } else {
                child.setTranslationY(targetTranslationY.toFloat())
            }
        }
    }
}
