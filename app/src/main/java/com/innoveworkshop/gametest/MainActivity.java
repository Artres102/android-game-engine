package com.innoveworkshop.gametest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.innoveworkshop.gametest.engine.Circle;
import com.innoveworkshop.gametest.engine.GameObject;
import com.innoveworkshop.gametest.engine.GameSurface;

public class MainActivity extends AppCompatActivity {
    protected GameSurface gameSurface;
    protected Button upButton;
    protected Button downButton;
    protected Button leftButton;
    protected Button rightButton;

    protected Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameSurface = (GameSurface) findViewById(R.id.gameSurface);
        game = new Game();
        gameSurface.setRootGameObject(game);

        setupControls();
    }

    private void setupControls() {
        upButton = (Button) findViewById(R.id.up_button);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.circle.position.y -= 10;
            }
        });

        downButton = (Button) findViewById(R.id.down_button);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.circle.position.y += 10;
            }
        });

        leftButton = (Button) findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.circle.position.x -= 10;
            }
        });

        rightButton = (Button) findViewById(R.id.right_button);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.circle.position.x += 10;
            }
        });
    }

    class Game extends GameObject {
        public Circle circle;

        @Override
        public void onStart(GameSurface surface) {
            super.onStart(surface);

            circle = new Circle(surface.getWidth() / 2, surface.getHeight() / 2, 100, Color.RED);
            surface.addGameObject(circle);
        }

        @Override
        public void onFixedUpdate() {
            super.onFixedUpdate();

            //circle.setPosition(circle.position.x + 1, circle.position.y + 1);
        }
    }
}