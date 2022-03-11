package com.group10.app.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChicken extends SuperObject {

    public ObjChicken(){

        name = "Chicken";

        try {
            image = ImageIO.read((getClass().getResourceAsStream("/collectibles/chickenDrumStick.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
