package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class vectorPractice extends ApplicationAdapter {
    ShapeRenderer renderer;
	OrthographicCamera camera;
	SpriteBatch batch;
	float time;

    ai ball;
    player p;

    enum Axis { X, Y };
    enum Direction { U, D, L, R };

    ArrayList<bullet> bullets = new ArrayList<bullet>();


    @Override
	public void create () {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		ball = new ai(new Texture("player.png"),100,100);
		p = new player(300,300,20,20,120f,new Texture("player.png"));
		time=0;
	}

    public void moveEntity(player e, float newX, float newY) {
        // just check x collisions keep y the same
        moveEntityInAxis(e, Axis.X, newX, e.y);
        // just check y collisions keep x the same
        moveEntityInAxis(e, Axis.Y, e.x, newY);
    }

    public void moveEntityInAxis(player e, Axis axis, float newX, float newY) {
        Direction direction;

        // determine axis direction
        if (axis == Axis.Y) {
            if (newY - e.y < 0) direction = Direction.U;
            else direction = Direction.D;
        } else {
            if (newX - e.x < 0) direction = Direction.L;
            else direction = Direction.R;
        }

        e.move(newX, newY);
    }

    @Override
	public void render () {

        float delta = Gdx.graphics.getDeltaTime();

        //moving player
        p.update(delta);
        moveEntity(p, p.x + p.dx, p.y + p.dy);

        //screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);




         //renderer.setProjectionMatrix(camera.combined);
         //renderer.begin(ShapeRenderer.ShapeType.Line);
         //renderer.setColor(1.0f, 1.0f, 1.0f, 1.0f);
         //renderer.circle(ball.position.x, ball.position.y, ball.radius);


        /*
        // Show the travel path of the ball if we were to click at the current location.
        if (Gdx.app.getType() == Application.ApplicationType.Desktop || Gdx.app.getType() == Application.ApplicationType.WebGL) {
            renderer.setColor(0.2f, 0.2f, 0.2f, 1.0f);
            renderer.line(ball.position.x, ball.position.y, Gdx.input.getX(), Gdx.graphics.getHeight()- Gdx.input.getY());
        }
        */
        //renderer.end();

        // Invert the the y to accommodate difference in coordinate systems
       if (Gdx.input.justTouched()) { //try isTouched for cool effect :)

           bullets.add(new bullet(new Texture("player.png"),p.x,p.y));
           for(bullet b : bullets) {
               if (b.shot==false) {
                   b.shootToward(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
               }
           }
       }


        for(int i = 0; i< bullets.size(); i++)
        {
            bullets.get(i).update(delta);
            if(bullets.get(i).Remove())
            {

                //bulletHitX = bullets.get(i).getX();
                //bulletHitY = bullets.get(i).getY();

                //bullets.add(new Bullet(bullets.get(i).getX(), bullets.get(i).getY(), Direction.U)); potential for grenades and other weapons

                bullets.remove(i);
                i--;
            }
        }

        //code relating to simple swarm A.I
        /*
        time+=delta;
        if(time>2) {
            ball.update(delta);
            ball.shootToward(p.x, p.y);
        }
        */


        batch.begin();
        ball.draw(batch);
        p.draw(batch);
        for(bullet b : bullets)
        {
            b.draw(batch);
        }
        batch.end();
	}
	
	@Override
	public void dispose () {

	}

}
