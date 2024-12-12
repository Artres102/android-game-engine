package com.innoveworkshop.gametest

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.innoveworkshop.gametest.assets.Football
import com.innoveworkshop.gametest.engine.Circle
import com.innoveworkshop.gametest.engine.GameObject
import com.innoveworkshop.gametest.engine.GameSurface
import com.innoveworkshop.gametest.engine.Rectangle
import com.innoveworkshop.gametest.engine.Vector

class MainActivity : AppCompatActivity() {
    protected var gameSurface: GameSurface? = null
    protected var upButton: Button? = null

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
        upButton!!.setOnClickListener { game!!.football!!.position.y = -100f; game!!.football!!.position.x = gameSurface!!.width.toFloat() / 2f}
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            // Pass the touch event to the football instance
            game?.football?.onTouchEvent(it)
        }
        return super.onTouchEvent(event)
    }

    inner class Game : GameObject() {
        var football: Football? = null

        override fun onStart(surface: GameSurface?) {
            super.onStart(surface)

            football = Football(
                    surface!!.width / 2.toFloat(), 0f, 250f, Color.BLUE, 1f
            )

            surface.addGameObject(football!!)

        }

        override fun onFixedUpdate() {
            super.onFixedUpdate()
        }
    }
}