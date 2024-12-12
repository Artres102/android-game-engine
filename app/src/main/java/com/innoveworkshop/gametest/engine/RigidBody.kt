package com.innoveworkshop.gametest.engine

import com.innoveworkshop.gametest.engine.Vector

open class RigidBody(
    var mass: Float,
    var velocity: Vector = Vector(0f, 0f)
) : GameObject() {
    fun applyForce(force: Vector) {
        // F = m * a => a = F / m
        val acceleration = Vector(force.x / mass, force.y / mass)
        velocity.x += acceleration.x
        velocity.y += acceleration.y
    }
    fun updatePositionY(
        initialYPosition: Float,
        initialYVelocity: Float,
        aceleration: Float,
        time: Float,
    ): Float{
        val tempY = initialYPosition + (initialYVelocity * time) + (aceleration) * (time*time)
        return tempY
    }

    fun updatePositionX(
        initialXPosition: Float,
        initialXVelocity: Float,
        aceleration: Float,
        time: Float,
    ): Float{
        //Log.d("TIME", time.toString())
        val tempX = initialXPosition + (initialXVelocity * time) + (aceleration) * (time*time)
        //Log.d("X: ", tempX.toString())
        return tempX
    }
    fun GetVelocityY(initial:Float, time:Float, grav:Float): Float {
        val velocity = (initial + (grav*time))
        return velocity
    }
    fun GetVelocityX(initial:Float, time:Float, acceleration:Float): Float {
        val velocity = (initial + (acceleration*time))
        return velocity
    }

}