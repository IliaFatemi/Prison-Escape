package com.group10.app.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * This is class for music and sound effects
 */

public class Sound {
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
    public Sound() {

        soundURL[0] = getClass().getResource("/sound/bgm.wav");
        soundURL[1] = getClass().getResource("/sound/getKey.wav");
        soundURL[2] = getClass().getResource("/sound/getTimer.wav");
        soundURL[3] = getClass().getResource("/sound/getChicken.wav");
        soundURL[4] = getClass().getResource("/sound/getTrap.wav");
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
}
