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

    var gravity: Float = 98.1f * 5f
    var windResistance: Float = 0f
    var deltatime: Float = 0.016f * 3
    var initialY: Float = y
    var initialX: Float = x
    var initialVelocityY: Float = 1000f
    var initialVelocityX: Float = 0f
    var time: Float = 0f


    override fun onFixedUpdate() {
        super.onFixedUpdate()


        if (this.position.x + this.radius > gameSurface!!.width || this.position.x - this.radius < 0) {
            rigidBody.velocity.x *= -0.75f
            if (time >= 3 * deltatime) {
                time = 0f
                initialVelocityX = rigidBody.velocity.x
                initialVelocityY = rigidBody.velocity.y
                initialY = this.position.y
                initialX = this.position.x
            }
        }

        if (isFloored) {
            rigidBody.velocity.y *= -0.75f
            if (time >= 2 * deltatime) {
                time = 0f
                initialVelocityX = rigidBody.velocity.x
                initialVelocityY = rigidBody.velocity.y
                initialY = this.position.y
                initialX = this.position.x
            }
        } else if (!isFloored) {
            if (time >= 10 * deltatime) {
                time = 0f
                initialVelocityX = rigidBody.velocity.x
                initialVelocityY = rigidBody.velocity.y
                initialY = this.position.y
                initialX = this.position.x
            }
        }

        if (time >= 1*deltatime) {
            position.y = rigidBody.updatePositionY(
                initialY,
                initialVelocityY,
                gravity,
                time
            )

            position.x = rigidBody.updatePositionX(
                initialX,
                initialVelocityX,
                windResistance,
                time
            )
        }

        time += deltatime

        rigidBody.velocity.y = rigidBody.GetVelocityY(initialVelocityY, time, gravity)
        rigidBody.velocity.x = rigidBody.GetVelocityX(initialVelocityX, time, windResistance)
    }

    var touchX: Float = 0f
    var touchY: Float = 0f
    fun onTouchEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Get the touch coordinates
                touchX = event.x
                touchY = event.y

                //Check if touch is on bottom side of the ball
                if (touchY > position.y && touchY < position.y + radius && touchX > position.x - radius && touchX < position.x + radius &&
                    touchY > position.y + radius / 2){
                    rigidBody.velocity.y = -1000f
                    initialVelocityX = rigidBody.velocity.x
                    initialVelocityY = rigidBody.velocity.y
                    initialY = position.y
                    initialX = position.x
                }

                if (touchY < position.y && touchY > position.y - radius && touchX > position.x - radius && touchX < position.x + radius &&
                    touchY > position.y - radius / 2){
                    rigidBody.velocity.y = 1000f
                    initialVelocityX = rigidBody.velocity.x
                    initialVelocityY = rigidBody.velocity.y
                    initialY = position.y
                    initialX = position.x
                }

                // Check if the touch is on the left side of the ball
                if (touchX < position.x && touchX > position.x - radius &&
                    touchY > position.y - radius && touchY < position.y + radius) {
                    rigidBody.velocity.x += 1000f
                    time = 0f
                    initialVelocityX = rigidBody.velocity.x
                    initialVelocityY = rigidBody.velocity.y
                    initialY = position.y
                    initialX = position.x
                }

                // Check if the touch is on the right side of the ball
                if (touchX > position.x && touchX < position.x + radius &&
                    touchY > position.y - radius && touchY < position.y + radius) {
                    rigidBody.velocity.x -= 1000f
                    time = 0f
                    initialVelocityX = rigidBody.velocity.x
                    initialVelocityY = rigidBody.velocity.y
                    initialY = position.y
                    initialX = position.x
                }
            }
        }
    }
}