package com.group10.app.SavedData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.FileInputStream;  

/**
 * LoadGame loads the last position of the player, the level, player position, time on the timer, the score, number of keys collected and enemy position. Load game will read up on a text file called save0.txt.
 * @author Ilia Fatemi
 */
public class LoadGame {
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

    /**
     * LoadGame constructor will load up the save file and it's contents
     */
    public LoadGame(){
        loadData();
    }

    /**
     * <p>loadData will load up the save0.txt file and store the values inside of a private array inside the LoadGame</p>
     */
    public void loadData(){
        try{
            System.out.println("Loading level...");
            System.out.println("getting saved data");
            InputStream level = new FileInputStream("src/main/SavedGame/save0.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(level));
            String line = br.readLine();
            int i = 0;
            String[] savedData = line.split(" ");

            while(i < data.length && savedData.length == data.length){
                int num = Integer.parseInt(savedData[i]);
                data[i] = num;
                i++;
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
    public int loadPlayerX(){System.out.println("loading position X");return data[1];}

    /**
     * <p>loadPlayerY will load up the players Y position on the map</p>
     * @return will return an Integer which will indicate the position. The number is based on pixels.
     */
    public int loadPlayerY(){System.out.println("loading position Y");return data[2];}

    /**
     * loadTimer will load the timer from the save file
     * @return will return an integer.
     */
    public int loadTimer(){System.out.println("loading timer");return data[3];}

    /**
     * loadScore will load the score from the save file
     * @return will return an integer.
     */
    public int loadScore(){System.out.println("loading score");return data[4];}

    /**
     * loadNumKeys will return the number of keys collected from the save file
     * @return will return an integer.
     */
    public int loadNumKeys(){System.out.println("loading keys");return data[5];}

    /**
     * loadEnemyX will load up the enemies X position
     * @return will return an integer.
     */
    public int loadEnemyX(){System.out.println("loading Enemy pos X");return data[6];}

    /**
     * loadEnemyY will load up the enemies Y position
     * @return will return an integer.
     */
    public int loadEnemyY(){System.out.println("loading Enemy pos Y");return data[7];}
}
