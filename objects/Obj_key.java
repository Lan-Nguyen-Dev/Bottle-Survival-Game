package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_key extends OBJECT {

    public Obj_key(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream("../res/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = "key";
    }
}
