package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class player {

    public float x;
    public float y;
    public float dx;
    public float dy;
    public int width;
    public int height;
    public float speed;
    public Texture texture;
    Sprite sprite;

    public player(float x, float y, int width, int height, float speed, Texture tex) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.texture = tex;
        sprite = new Sprite(texture);
        sprite.setPosition(x,y);
    }

    public void move(float newX, float newY) {
        x = newX;
        y = newY;

        sprite.setPosition(x,y);
    }

    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }

    public void update(float delta) {

        dx = 0;
        dy = 0;

        // move
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dy = speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dy = -speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dx = -speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dx = speed * delta;
        }
    }

}
