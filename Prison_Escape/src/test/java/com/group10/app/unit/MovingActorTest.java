package com.group10.app.unit;
import com.group10.app.main.GamePanel;
import com.group10.app.entity.nonStaticEntities.MovingActor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovingActorTest {
    private GamePanel gp;
    MovingActor actor;
    @BeforeEach
    public void setUp(){
        gp = new GamePanel();
        actor = new MovingActor(gp);
        actor.setX(40);
        actor.setY(80);
    }
    
    @Test
    public void setSpeedTest(){
        actor.setSpeed(0);
        assertEquals(0, actor.getSpeed());
        actor.setSpeed(2000000);
        assertEquals(2000000, actor.getSpeed());
    }

    @Test
    public void setDirectionTest(){
        assertEquals("down", actor.getDirection());
        actor.setDirection("up");
        assertEquals("up", actor.getDirection());
        actor.setDirection("down");
        assertEquals("down", actor.getDirection());
        actor.setDirection("left");
        assertEquals("left", actor.getDirection());
        actor.setDirection("right");
        assertEquals("right", actor.getDirection());
    }

    @Test
    public void setSpriteNumTest(){
        assertEquals(1, actor.getSpriteNum());
        actor.setSpriteNum(2);
        assertEquals(2, actor.getSpriteNum());
        actor.setSpriteNum(3);
        assertEquals(3, actor.getSpriteNum());
    }

    @Test
    public void setCollisionTest(){
        assertEquals(false, actor.getCollision());
        actor.setCollision(true);
        assertEquals(true, actor.getCollision());
        actor.setCollision(false);
        assertEquals(false, actor.getCollision());
    }

    @Test
    public void getXYTest(){
        assertEquals(40, actor.getX());
        assertEquals(80, actor.getY());
        actor.setX(100);
        actor.setY(0);
        assertEquals(100, actor.getX());
        assertEquals(0, actor.getY());
    }

    @Test
    public void collisionUpdateTest(){
        actor.setCollision(false);
        actor.setX(1);
        actor.setY(2);
        actor.setSpeed(1);
        actor.setDirection("up");
        actor.collisionUpdate();
        assertEquals(1, actor.getY());
        actor.setDirection("down");
        actor.collisionUpdate();
        assertEquals(2, actor.getY());
        actor.setDirection("left");
        actor.collisionUpdate();
        assertEquals(0, actor.getX());
        actor.setDirection("right");
        actor.collisionUpdate();
        assertEquals(1, actor.getX());
    }
}
