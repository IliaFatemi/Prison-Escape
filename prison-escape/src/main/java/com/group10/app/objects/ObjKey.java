package com.group10.app.objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject {

    public ObjKey(){

        name = "Key";

        try {
            image = ImageIO.read((getClass().getResourceAsStream("/collectibles/key.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}