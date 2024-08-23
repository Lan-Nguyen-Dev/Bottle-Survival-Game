package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_pants extends OBJECT {
    public Obj_pants(){
        try {
            img = ImageIO.read(getClass().getResourceAsStream("../res/objects/pants.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = "pants";
    }
}
