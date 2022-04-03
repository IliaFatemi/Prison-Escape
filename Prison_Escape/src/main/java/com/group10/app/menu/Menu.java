package com.group10.app.menu;

import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;

import java.awt.*;

public class Menu {

    GamePanel gp;

    public Menu(GamePanel gp){
        this.gp = gp;

    }

    public void registerMenuGraphics(){

    }

    public Menu checkMenuType(){
        System.out.println(gp.getState());
        if(gp.getState() == GameStates.PAUSED) {
            return new PauseMenu(gp);
        }else if(gp.getState() == GameStates.MENU){
            return new MenuScreen(gp);
        }else if(gp.getState() == GameStates.GAMEWON){
            return new WonMenu(gp);
        }else if(gp.getState() == GameStates.GAMEOVER){
            return new GameOverMenu(gp);
        }
        return null;
    }

    public void renderMenu(Graphics2D g2){

    }
}
