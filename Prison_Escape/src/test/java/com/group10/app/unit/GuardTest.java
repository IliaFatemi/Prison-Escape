package com.group10.app.unit;

import com.group10.app.entity.nonStaticEntities.Guard;
import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.main.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GuardTest {
    GamePanel gp;
    KeyManager keyH;

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

    @Test
    public void testGuardUpdate(){
        gp = new GamePanel();
        gp.inmate = new Inmate(gp, keyH);
        Guard guard = new Guard(gp);
        gp.inmate.setPos(100, 100);
        guard.setGuardValues(150, 100);
        guard.update();
        Assert.assertEquals("left", guard.getDirection());
        gp.inmate.setPos(100, 100);
        guard.setGuardValues(50, 100);
        guard.update();
        Assert.assertEquals("right", guard.getDirection());
        gp.inmate.setPos(100, 100);
        guard.setGuardValues(100, 150);
        guard.update();
        Assert.assertEquals("up", guard.getDirection());
        gp.inmate.setPos(100, 100);
        guard.setGuardValues(100, 50);
        guard.update();
        Assert.assertEquals("down", guard.getDirection());
    }
}


