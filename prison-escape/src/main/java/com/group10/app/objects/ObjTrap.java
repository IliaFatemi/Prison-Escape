package com.group10.app.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjTrap extends SuperObject {

    public ObjTrap(){

        name = "Trap";

        try {
            image = ImageIO.read((getClass().getResourceAsStream("/trap/trap.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
