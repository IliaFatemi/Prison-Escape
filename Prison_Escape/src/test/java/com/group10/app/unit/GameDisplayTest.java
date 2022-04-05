package com.group10.app.unit;

import com.group10.app.main.GamePanel;
import com.group10.app.main.GameDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameDisplayTest {
    private GamePanel gp;
    private GameDisplay gd;

    @BeforeEach
    public void setUp(){
        gp = new GamePanel();
        gd = new GameDisplay(gp);

    }

    @Test
    public void addMessageEmptyTextTest(){

        String test = "";
        gd.addMessage(test);
        assertEquals(1, gd.getMessageCounter().size());

    }

    @Test
    public void add1MessageTextTest(){
        String test1 = "";
        String test2 = "";
        String test3 = "";
        String test4 = "";

        gd.addMessage(test1);
        gd.addMessage(test2);
        gd.addMessage(test3);
        gd.addMessage(test4);

        assertEquals(4, gd.getMessageCounter().size());
    }
}
