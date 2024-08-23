package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_door extends OBJECT {
    public Obj_door(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream("../res/objects/block-door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = "door";
    }
}
