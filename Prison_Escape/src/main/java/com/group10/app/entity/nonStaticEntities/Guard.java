package com.group10.app.entity.nonStaticEntities;


import com.group10.app.main.GamePanel;

/**
 * The guard class in charge of the Guard implementation
 *
 * <p>
 *     This class implements the guard functionality for the game
 *     manages the guards movements, interactions, drawing the sprites and
 *     changing them with the guards movements.
 * </p>
 *
 */
public class Guard extends MovingActor {
    GamePanel gp;
    boolean moving = false;
    int pixelCounter = 0;

    /**
     * The constructor for the Guard class...
     *
     * <p>
     *     Default setting include: direction = down
     *                              speed = 1
     *                              SolidArea(8, 16, 32, 32)
     *                              Get guard's images
     * </p>
     *
     * @param gp main game panel
     */
    public Guard(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Guard";
        setDirection("down");
        setSpeed(1);
        setSolidHeight(31);

        getGuardImage();

    }

    /**
     * getGuardImage method is in charge of registering the image directories for the guard enemy
     */
    public void getGuardImage(){
        up1 = setup("/prisonGuard/WalkUp1");
        up2 = setup("/prisonGuard/WalkUp2");
        up3 = setup("/prisonGuard/WalkUp3");
        down1 = setup("/prisonGuard/WalkDown1");
        down2 = setup("/prisonGuard/WalkDown2");
        down3 = setup("/prisonGuard/WalkDown3");
        left1 = setup("/prisonGuard/WalkLeft1");
        left2 = setup("/prisonGuard/WalkLeft2");
        left3 = setup("/prisonGuard/WalkLeft3");
        right1 = setup("/prisonGuard/WalkRight1");
        right2 = setup("/prisonGuard/WalkRight2");
        right3 = setup("/prisonGuard/WalkRight3");
    }

    /**
     * set the value for guard
     *
     * @param x value to set guards x position
     * @param y value to set guards y position
     */
    public void setGuardValues(int x, int y){
        setX(x);
        setY(y);
        setSpeed(1);
        moving = false;
        setDirection("default");
    }

    /**
     * update method is in charge of updating the position of the guard
     * <p>
     *     The update method in the Guard class is responsible for the movement
     *     of the guard when the inmate comes within range of the guard. Once the inmate
     *     is in range the guard will follow the player
     * </p>
     */
    public void update() {

        if (!moving) {

            if (gp.inmate.getX() < getX()) {
                setDirection("left");
            }
            else if (gp.inmate.getX() > getX()) {
                setDirection("right");
            }

            if (Math.abs(gp.inmate.getX() - getX()) < gp.cellSize){
                if (gp.inmate.getY() < getY()) {
                    setDirection("up");
                }
                else if (gp.inmate.getY() > getY()) {
                    setDirection("down");
                }
            }

            moving = true;
        }

        collision = false;
        collisionUpdate();
        spriteUpdate();

        pixelCounter += getSpeed();

        if (pixelCounter == 48) {
            moving = false;
            pixelCounter = 0;
        }
    }

}
