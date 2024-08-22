package entity;

import java.awt.Rectangle;

public class Entity {
    public Rectangle hitbox;
    public String direction = "down";
    
    public int worldX, worldY;
    public int screenX, screenY;

    public boolean collision;

    public int speed;
}
