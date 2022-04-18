package com.group10.app.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

/**
 * This is class for music and sound effects
 */

public class SoundManager {
    Clip clip;
    URL[] soundURL = new URL[10];

    /**
     * This is the constructor for the Sound class.
     *
     * <p>
     *     It is in charge of registering the sound directories.
     * </p>
     *
     */
    public SoundManager() { setup(); }

    /**
     * Read from the file that stored all sounds.
     * Insert sounds into soundURL.
     *
     * <p>
     *     The sound file in the format of integer, sound_name.wav
     *     Separate by a space.
     * </p>
     */
    public void setup() {

        try {
            File file = new File("src/main/resources/sound/soundSetUp.txt");
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] setUpValues = data.split(" ");
                soundURL[Integer.parseInt(setUpValues[0])] = getClass().getResource("/sound/" + setUpValues[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Get the sound that need to play
     *
     * <p>
     *     This method will get the sound from the soundURL list by passing the index i.
     * </p>
     *
     * @param i index of sound in soundURL list
     */
    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception ignored){}
    }

    /**
     * Play the current sound.
     */
    public void play() {
        clip.start();
    }

    /**
     * Loop the current sound.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop the current sound.
     */
    public void stop() {
        clip.stop();
    }


    /**
     * Get the music we need by using setFile, and play it forever.
     * @param i the index of soundURL
     */
    public void playMusic (int i) {
        if (clip != null) {
            stop();
        }
        setFile(i);
        play();
        loop();
    }

    /**
     * Stop Music
     */
    public void stopMusic () {
        stop();

    }

    /**
     * Play sound effect that index i in soundURL
     * @param i the index of soundURL
     */
    public void playSE(int i) {
        double drawInterval = 1000000000/ 60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        try {    setFile(i);    play();    double remainingTime = nextDrawTime - System.nanoTime();    
            remainingTime /= 1000000;    
            if(remainingTime < 0){        remainingTime = 0;    
            }    
            Thread.sleep((long) remainingTime);
        } catch (InterruptedException e) {    
            e.printStackTrace();
        }
    }
}
