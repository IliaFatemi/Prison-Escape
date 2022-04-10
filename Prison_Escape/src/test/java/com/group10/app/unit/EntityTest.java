package com.group10.app.unit;
import com.group10.app.main.GamePanel;
import com.group10.app.entity.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {
    private GamePanel gp;
    Entity e;

    @BeforeEach
    public void setUp(){
        gp = new GamePanel();
        e = new Entity(gp);
        e.setX(40);
        e.setY(80);
    }

    @Test 
    public void getXYTest(){
        assertEquals(40, e.getX());
        assertEquals(80, e.getY());
        e.setX(0);
        e.setY(0);
        assertEquals(0, e.getX());
        assertEquals(0, e.getY());
    }
}
