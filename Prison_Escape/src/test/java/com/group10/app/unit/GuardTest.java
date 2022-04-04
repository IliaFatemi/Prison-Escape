package com.group10.app.unit;

import com.group10.app.entity.nonStaticEntities.Guard;
import com.group10.app.main.*;

import org.junit.Assert;
import org.junit.Test;

public class GuardTest {
    GamePanel gp;

    @Test
    public void testPosition(){
        Guard guard= new Guard(gp);
        guard.setGuardValues(50, 50);
        Assert.assertEquals(50, (int)guard.getX());
        Assert.assertEquals(50, (int)guard.getY());
        guard.setGuardValues(150, 550);
        Assert.assertEquals(150, (int)guard.getX());
        Assert.assertEquals(550, (int)guard.getY());
    }

    @Test
    public void testDirection(){
        Guard guard = new Guard(gp);
        guard.setDirection("up");
        Assert.assertEquals("up", guard.getDirection());
        guard.setDirection("down");
        Assert.assertEquals("down", guard.getDirection());
        guard.setDirection("left");
        Assert.assertEquals("left", guard.getDirection());
        guard.setDirection("right");
        Assert.assertEquals("right", guard.getDirection());
    }

    @Test
    public void testSpeed(){
        Guard guard = new Guard(gp);
        guard.setSpeed(0);
        Assert.assertEquals(0, (int)guard.getSpeed());
        guard.setSpeed(2);
        Assert.assertEquals(2, (int)guard.getSpeed());
        guard.setSpeed(2000);
        Assert.assertEquals(2000, (int)guard.getSpeed());
        guard.setSpeed(-280);
        Assert.assertEquals(-280, (int)guard.getSpeed());
    }
}


