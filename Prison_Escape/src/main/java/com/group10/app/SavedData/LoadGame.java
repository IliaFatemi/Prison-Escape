package com.group10.app.SavedData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.FileInputStream;  

public class LoadGame {
    // index 0: level
    // index 1: player x cor
    // index 2: player y cor
    // index 3: timer
    // index 4: score
    // index 5: number of keys
    // index 6: enemy x cor
    // index 7: enemy y cor
    int[] data = new int[8];

    public LoadGame(){
        loadData();
    }

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
    
    public int loadLevel(){System.out.println("loading level");return data[0];}
    public int loadPlayerX(){System.out.println("loading position X");return data[1];}
    public int loadPlayerY(){System.out.println("loading position Y");return data[2];}
    public int loadTimer(){System.out.println("loading timer");return data[3];}
    public int loadScore(){System.out.println("loading score");return data[4];}
    public int loadNumKeys(){System.out.println("loading keys");return data[5];}
    public int loadEnemyX(){System.out.println("loading Enemy pos X");return data[6];}
    public int loadEnemyY(){System.out.println("loading Enemy pos Y");return data[7];}
}
