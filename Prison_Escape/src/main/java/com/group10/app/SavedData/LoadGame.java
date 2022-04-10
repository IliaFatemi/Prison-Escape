package com.group10.app.SavedData;

import com.group10.app.entity.nonStaticEntities.Guard;
import com.group10.app.main.GamePanel;
import com.group10.app.entity.staticEntities.*;

import java.io.*;
import java.util.Arrays;

/**
 * LoadGame loads the last position of the player, the level, player position, time on the timer, the score, number of keys collected and enemy position. Load game will read up on a text file called save0.txt.
 * @author Ilia Fatemi
 */
public class LoadGame {
    GamePanel gp;
    private String fileName;

    /**
     * All the data from the text file will be stored in the data array.
     * index 0: level
     * index 1: player x cor
     * index 2: player y cor
     * index 3: timer
     * index 4: score
     * index 5: number of keys
     * index 6: enemy x cor
     * index 7: enemy y cor
     */
    int[] data = new int[8];

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * LoadGame constructor will load up the save file and it's contents
     */
    public LoadGame(GamePanel gp){
        this.gp = gp;
    }


    /**
     * <p>loadData will load up the save0.txt file and store the values inside of a private array inside the LoadGame</p>
     */
    public void loadData(String fileName){
        setFileName(fileName);

        try{
            System.out.println("Loading level...");
            System.out.println("getting saved data");
            InputStream level = new FileInputStream("src/main/SavedGame/" + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(level));
            String line = br.readLine();
            String[] savedData = line.split(" ");

            GamePanel.GAME_LEVEL = Integer.parseInt(savedData[0]);
            gp.inmate.setX(Integer.parseInt(savedData[1]));
            gp.inmate.setY(Integer.parseInt(savedData[2]));
            gp.inmate.setSpeed(Integer.parseInt(savedData[3]));
            gp.inmate.setTimer(Double.parseDouble(savedData[4]));
            gp.inmate.setScore(Integer.parseInt(savedData[5]));
            gp.inmate.setNumKeys(Integer.parseInt(savedData[6]));

            Arrays.fill(gp.obj, null);
            Arrays.fill(gp.guard, null);

            gp.tileManage.loadMap("/levels/Level" + GamePanel.GAME_LEVEL + ".txt");
            System.out.println("load map");

            int objNum = Integer.parseInt(br.readLine());

            for (int i = 0; i < objNum; i++) {

                String obj = br.readLine();
                String[] objInfo = obj.split(" ");
                String name = objInfo[0];
                int x = Integer.parseInt(objInfo[1]);
                int y = Integer.parseInt(objInfo[2]);

                switch (name) {
                    case "Chicken":
                        gp.asset.createObj(new Chicken(gp), x, y);
                        gp.obj[i].disappears = Integer.parseInt(objInfo[3]);
                        break;
                    case "Key":
                        gp.asset.createObj(new Key(gp), x, y);
                        break;
                    case "Timer":
                        gp.asset.createObj(new Timer(gp), x, y);
                        break;
                    case "Trap":
                        gp.asset.createObj(new Trap(gp), x, y);
                        break;
                    case "Door":
                        gp.asset.createObj(new Door(gp), x, y);
                        break;
                }
            }

            int guardNum = Integer.parseInt(br.readLine());
            for (int j = 0; j < guardNum; j++) {
                String guard = br.readLine();
                String[] guardInfo = guard.split(" ");
                int x = Integer.parseInt(guardInfo[0]);
                int y = Integer.parseInt(guardInfo[1]);
                gp.asset.createGuard(new Guard(gp), x, y);
            }

            br.close();
            System.out.println("Data loaded");

        }catch(Exception e){
            System.out.println("Failed to load save file");
            e.printStackTrace();
        }
    }
    
    /**
     * <p>loadLevel will load up the level number from the save file</p>
     * @return will return an Integer which indicates the level
     */
    public int loadLevel(){System.out.println("loading level");return data[0];}

    /**
     * <p>loadPlayerX will load up the players X position on the map</p>
     * @return will return an Integer which will indicate the position. The number is based on pixels.
     */
    public int loadPlayerX(){System.out.println("loading position X " + data[1]);return data[1];}

    /**
     * <p>loadPlayerY will load up the players Y position on the map</p>
     * @return will return an Integer which will indicate the position. The number is based on pixels.
     */
    public int loadPlayerY(){System.out.println("loading position Y");return data[2];}

    /**
     * <p>loadTimer will load the timer from the save file</p>
     * @return will return an integer.
     */
    public int loadTimer(){System.out.println("loading timer");return data[3];}

    /**
     * <p>loadScore will load the score from the save file</p>
     * @return will return an integer.
     */
    public int loadScore(){System.out.println("loading score");return data[4];}

    /**
     * <p>loadNumKeys will return the number of keys collected from the save file</p>
     * @return will return an integer.
     */
    public int loadNumKeys(){System.out.println("loading keys");return data[5];}

    /**
     * <p>loadEnemyX will load up the enemies X position</p>
     * @return will return an integer.
     */
    public int loadEnemyX(){System.out.println("loading Enemy pos X");return data[6];}

    /**
     * <p>loadEnemyY will load up the enemies Y position</p>
     * @return will return an integer.
     */
    public int loadEnemyY(){System.out.println("loading Enemy pos Y");return data[7];}
}
