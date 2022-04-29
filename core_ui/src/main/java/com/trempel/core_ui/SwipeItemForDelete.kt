package com.trempel.core_ui

import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.View

class SwipeItemForDelete : View.OnTouchListener {

    var startX = 0f
    var endX = 0f

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                true
            }
            MotionEvent.ACTION_UP -> {
                endX = event.x
                swipe(v)
            }
            else -> false
        }
    }

    private fun swipe(view: View?): Boolean {
        return when {
            endX < startX -> {
                ObjectAnimator.ofFloat(view, "translationX", -200f).apply {
                    duration = 80
                    start()
                }
                true
            }
            endX > startX -> {
                ObjectAnimator.ofFloat(view, "translationX", 0f).apply {
                    duration = 80
                    start()
                }
                true
            }
            else -> {
                ObjectAnimator.ofFloat(view, "translationX", 0f).apply {
                    duration = 80
                    start()
                }
                view?.performClick()
                false
            }
        }
    }
}
