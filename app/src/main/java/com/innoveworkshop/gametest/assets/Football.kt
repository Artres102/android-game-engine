package com.innoveworkshop.gametest.assets

import android.view.MotionEvent
import com.innoveworkshop.gametest.engine.Circle
import com.innoveworkshop.gametest.engine.RigidBody
import com.innoveworkshop.gametest.engine.Vector

class Football(
    x: Float,
    y: Float,
    radius: Float,
    dropRate: Float,
    color: Int,
    mass: Float
) : Circle(x, y, radius, color) {
    private val rigidBody: RigidBody = RigidBody(mass)

    var dropRate: Float = dropRate

    init {
        this.dropRate = dropRate
    }

    fun applyForce(force: Vector) {
        rigidBody.applyForce(force)
    }

    override fun onFixedUpdate() {
        super.onFixedUpdate()

        if (!isFloored) {
            // Apply gravity or drop rate as a force
            applyForce(Vector(0f, dropRate))
            rigidBody.updatePosition(position, 1f)
        }
    }
}