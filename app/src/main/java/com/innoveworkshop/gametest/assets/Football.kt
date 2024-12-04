package com.innoveworkshop.gametest.assets

import android.view.MotionEvent
import com.innoveworkshop.gametest.engine.Circle
import com.innoveworkshop.gametest.engine.Vector

class Football(
    x: Float,
    y: Float,
    radius: Float,
    dropRate: Float,
    color: Int
    ) : Circle(x, y, radius, color) {
        var dropRate: Float = 0f

    init {
        this.dropRate = dropRate
    }

    override fun onFixedUpdate() {
        super.onFixedUpdate()

        if (!isFloored) position.y += dropRate
    }
}