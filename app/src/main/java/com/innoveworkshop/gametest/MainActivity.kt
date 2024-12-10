package com.innoveworkshop.gametest

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.innoveworkshop.gametest.assets.DroppingRectangle
import com.innoveworkshop.gametest.assets.Football
import com.innoveworkshop.gametest.engine.Circle
import com.innoveworkshop.gametest.engine.GameObject
import com.innoveworkshop.gametest.engine.GameSurface
import com.innoveworkshop.gametest.engine.Rectangle
import com.innoveworkshop.gametest.engine.Vector

class MainActivity : AppCompatActivity() {
    protected var gameSurface: GameSurface? = null
    protected var upButton: Button? = null
    protected var downButton: Button? = null
    protected var leftButton: Button? = null
    protected var rightButton: Button? = null

    protected var game: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameSurface = findViewById<View>(R.id.gameSurface) as GameSurface
        game = Game()
        gameSurface!!.setRootGameObject(game)

        setupControls()
    }

    private fun setupControls() {
        upButton = findViewById<View>(R.id.up_button) as Button
        upButton!!.setOnClickListener { game!!.football!!.velocity.y = -100f }

        downButton = findViewById<View>(R.id.down_button) as Button
        downButton!!.setOnClickListener { game!!.football!!.position.y += 10f }

        leftButton = findViewById<View>(R.id.left_button) as Button
        leftButton!!.setOnClickListener { game!!.football!!.velocity.x -= 1000f }

        rightButton = findViewById<View>(R.id.right_button) as Button
        rightButton!!.setOnClickListener { game!!.football!!.position.x += 10f }
    }

    inner class Game : GameObject() {
        var circle: Circle? = null
        var football: Football? = null

        override fun onStart(surface: GameSurface?) {
            super.onStart(surface)

            circle = Circle(
                (surface!!.width / 2).toFloat(),
                (surface.height / 2).toFloat(),
                100f,
                Color.RED
            )
            surface.addGameObject(circle!!)

            surface.addGameObject(
                Rectangle(
                    Vector((surface.width / 3).toFloat(), (surface.height / 3).toFloat()),
                    200f, 100f, Color.GREEN
                )
            )

            football = Football(
                    surface.width / 2.toFloat(), 0f, 250f, Color.BLUE, 1.0f
            )

            surface.addGameObject(football!!)

        }

        override fun onFixedUpdate() {
            super.onFixedUpdate()

            if (!circle!!.isFloored && !circle!!.hitRightWall() && !circle!!.isDestroyed) {
                circle!!.setPosition(circle!!.position.x + 1, circle!!.position.y + 1)
            } else {
                circle!!.destroy()
            }
        }
    }
}