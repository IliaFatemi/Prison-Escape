package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.entity.staticEntities.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.group10.app.main.GameStates.*;

/**
 * GameDisplay class is for show information in screen when game state.
 *
 * <p>
 *     This class includes methods that shows the inmate's information.
 *     And messages that show objects that inmate gets.
 * </p>
 */
public class GameDisplay {

    private GamePanel gp;
    private Graphics2D g2;
    private Font arial_40;
    private BufferedImage keyImage;
    private ArrayList<String> message = new ArrayList<>();
    private ArrayList<Integer> messageCounter = new ArrayList<>();


    public GamePanel getGp() {
        return gp;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public ArrayList<Integer> getMessageCounter() {
        return messageCounter;
    }

    DecimalFormat dFormat = new DecimalFormat("#0.0");

    /**
     * The constructor for the GameDisplay class
     *
     * @param gp passing gp to charge as a parameter
     */
    public GameDisplay(GamePanel gp) {

        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);

        Entity key = new Key(gp);
        keyImage = key.down1;
    }

    /**
     * If the GamePanel State is Game, show inmate's information by using drawScoreTimeKey
     * and show messages by using drawMessage
     *
     * @param g2 passing gp to charge as a parameter
     */
    public void draw (Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //Play state
        if (GamePanel.state == GAME){
            drawScoreTimeKey();
            drawMessage();
        }
    }

    /**
     * Add text into the message list,
     * and set the corresponding showtime counting into messageCounter list.
     *
     * @param text the message
     */
    public void addMessage (String text) {
        message.add(text);
        messageCounter.add(0);
    }

    /**
     * Show the inmate's Score, Time and the number of keys.
     */
    public void drawScoreTimeKey() {

        // Draw the Score
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("Score: " + dFormat.format(gp.inmate.getScore()), gp.cellSize, gp.cellSize);

        // Draw the Time
        gp.inmate.setTimer(gp.inmate.getTimer() - (double) 1/60);
        g2.drawString("Time: " + dFormat.format(gp.inmate.getTimer()), gp.cellSize * 11, gp.cellSize);

        // Draw the Key
        g2.drawImage(keyImage, gp.cellSize * 21, gp.cellSize - 32, null);
        g2.drawString("X " + gp.inmate.getNumKeys(), gp.cellSize * 22, gp.cellSize);

    }

    /**
     * Show message when inmate get an object.
     *
     * <p>
     *     This method will show messages one by one in message list.
     *     Each time the message show in the screen, the corresponding time plus one.
     *     The contain time of each message is determined by the corresponding
     *     time in messageCounter list.
     *     When the time large than 180 (3 seconds), the message will be deleted in message list.
     *     The next message will always show the down of the previous message.
     * </p>
     */
    public void drawMessage() {

        int messageX = gp.cellSize;
        int messageY = gp.cellSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++){

            if (message.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; //messageCounter++
                messageCounter.set(i, counter); //Set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
}
