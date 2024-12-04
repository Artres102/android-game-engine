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

    fun updatePosition(position: Vector, deltaTime: Float) {
        position.x += velocity.x * deltaTime
        position.y += velocity.y * deltaTime
    }
}