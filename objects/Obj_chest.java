package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_chest extends OBJECT {
    public Obj_chest(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream("../res/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = "chest";
    }
}
