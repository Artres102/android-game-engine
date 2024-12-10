package com.innoveworkshop.gametest.assets

import android.view.MotionEvent
import com.innoveworkshop.gametest.engine.Circle
import com.innoveworkshop.gametest.engine.RigidBody
import com.innoveworkshop.gametest.engine.Vector

class Football(
    x: Float,
    y: Float,
    radius: Float,
    color: Int,
    mass: Float
) : Circle(x, y, radius, color) {
    val rigidBody: RigidBody = RigidBody(mass)
    var velocity: Vector = rigidBody.velocity

    var gravity: Float = 98.1f * 2
    var deltatime: Float = 1f / 60f


    fun applyForce(force: Vector) {
        rigidBody.applyForce(force)
    }

    override fun hitLeftWall(): Boolean {
        return super.hitLeftWall()
    }

    override fun onFixedUpdate() {
        super.onFixedUpdate()

        rigidBody.velocity = velocity


        if (this.position.x + this.radius > gameSurface!!.width || this.position.x - this.radius < 0) {
            velocity.x *= -0.75f
        }
        if (!isFloored) {
            // Apply gravity or drop rate as a force
            applyForce(Vector(0f, gravity))
        } else {
            velocity.y *= -0.75f
        }
        rigidBody.updatePosition(position, deltatime)
    }
}