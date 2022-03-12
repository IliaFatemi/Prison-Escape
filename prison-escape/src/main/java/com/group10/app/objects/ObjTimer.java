package com.group10.app.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjTimer extends SuperObject {

    public ObjTimer(){

        name = "Timer";

        try {
            image = ImageIO.read((getClass().getResourceAsStream("/collectibles/timer.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
