package objects;

import java.awt.image.BufferedImage;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class OBJECT {
    public String name;
    public boolean collision = true;
    public BufferedImage img;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,96,96);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel){
        int playerWX = gamePanel.player.worldX;
        int playerWY = gamePanel.player.worldY;

        int screenOX = playerWX - gamePanel.screen_width/2;
        int screenOY = playerWY - gamePanel.screen_height/2;

        int tileWX = worldX ;
        int tileWY = worldY ;

        int tileSX = tileWX - screenOX;
        int tileSY = tileWY - screenOY;

        if(tileWX < screenOX - gamePanel.tile_size 
        || tileWY < screenOY - gamePanel.tile_size
        || tileWX > screenOX + gamePanel.screen_width
        || tileWY > screenOY + gamePanel.screen_height) return;

        g2.drawImage(
            img,
            (tileSX),
            (tileSY),
            gamePanel.tile_size,
            gamePanel.tile_size,
            null);

        
    }
   
}
