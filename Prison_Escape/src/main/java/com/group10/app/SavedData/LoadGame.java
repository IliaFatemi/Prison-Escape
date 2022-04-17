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
    private InputStream level;
    private FileOutputStream newFile;
    private BufferedReader br;

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
            level = new FileInputStream("src/main/SavedGame/" + fileName);
            br = new BufferedReader(new InputStreamReader(level));

            loadInmate();
            gp.tileManage.loadMap("/levels/Level" + gp.getGameLevel() + ".txt");
            loadEntity();
            loadGuard();

            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadInmate() {

        try{
            String line = br.readLine();
            String[] savedData = line.split(" ");
            gp.setGameLevel(Integer.parseInt(savedData[0]));
            gp.inmate.setX(Integer.parseInt(savedData[1]));
            gp.inmate.setY(Integer.parseInt(savedData[2]));
            gp.inmate.setSpeed(Integer.parseInt(savedData[3]));
            gp.inmate.setTimer(Double.parseDouble(savedData[4]));
            gp.inmate.setScore(Integer.parseInt(savedData[5]));
            gp.inmate.setNumKeys(Integer.parseInt(savedData[6]));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadEntity() {

        try{
            Arrays.fill(gp.obj, null);
            Arrays.fill(gp.guard, null);

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

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadGuard() {

        try{
            int guardNum = Integer.parseInt(br.readLine());

            for (int j = 0; j < guardNum; j++) {
                String guard = br.readLine();
                String[] guardInfo = guard.split(" ");
                int x = Integer.parseInt(guardInfo[0]);
                int y = Integer.parseInt(guardInfo[1]);
                gp.asset.createGuard(new Guard(gp), x, y);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isEmpty(String fileName) {
        File file = new File("src/main/SavedGame/" + fileName);
        return file.length() == 0;
    }

    /**
     * <p>This method will overwrite an existing file. If that file does not exists, a file of the given type will be created.</p>
     * @param file A file name. eg. "Test.txt"
     */
    public void resetFile(String file){
        try{
            newFile = new FileOutputStream("src/main/SavedGame/" + file);
            newFile.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
