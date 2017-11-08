package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class bullet {
    float speedMax = 350;
    Texture texture;
    Sprite sprite;
    float radius = 10;
    Vector2 position;
    Vector2 velocity;
    float x,y;
    boolean remove;
    private float lifeTime;
    private float lifeTimer;
    boolean shot;

    public bullet(Texture t,float x, float y){
        texture = t;
        sprite = new Sprite(texture);
        position = new Vector2();
        velocity = new Vector2();
        this.x = x;
        this.y = y;
        sprite.setPosition(x,y);
        position.x = x;
        position.y = y;
        lifeTimer = 0;
        lifeTime = 1.5f; //how long the bullet lasts for
        shot = false;


    }

    public boolean Remove()
    {
        return remove;
    }

    /** Shoot the ball toward the designated position */
    public void shootToward(float targetX, float targetY) {
            /*
             * Get the normalized direction vector from our position to the target. Then scale that value to our desired speed. In
             * this particular example, we are using the distance of the target from the current position to determine how fast we
             * will shoot the ball, and limiting to a maximum speed. We will apply velocity in the update method.
             */
        velocity.set(targetX - position.x, targetY - position.y).nor().scl(speedMax); //scaled to speedMax (refer to ai class for example mentioned above)
        shot =  true;
    }

    public void update(float deltaTime) {

        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        //velocity.scl(1 - (0.98f * deltaTime)); // Linear dampening, otherwise the ball will keep going at the original velocity forever

        sprite.setX(position.x);
        sprite.setY(position.y);

        lifeTimer += deltaTime;
        if (lifeTimer > lifeTime)
        {
            remove = true;
        }
    }

    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }
}
