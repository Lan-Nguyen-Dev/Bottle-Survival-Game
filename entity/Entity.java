package entity;

import java.awt.Rectangle;

public class Entity {
    public Rectangle hitbox;
    public int hitboxDefaultX = 0;
    public int hitboxDefaultY = 0;

    public String direction = "down";
    
    public int worldX, worldY;
    public int screenX, screenY;

    public boolean collision;

    public int speed;
}
