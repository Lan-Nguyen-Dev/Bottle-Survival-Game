package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    boolean isMove = false;

    int spriteIndex = 1;
    int spriteIndex_timer = 0;

    public Player(GamePanel gp, KeyHandler kh){
        this.gamePanel = gp;
        this.keyHandler = kh;
    }

    public void setDefaultValues(){
        x = 0;
        y = 0;
        speed = 4;
    }

    public void getPlayerImages(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-up-2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-down-2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-left-2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player-right-2.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(){
        if(keyHandler.isMoveDown==true){
            y+=speed;
            direction = "down";
            isMove = true;
        }
        else if(keyHandler.isMoveUp==true){
            y-=speed;
            direction = "up";
            isMove = true;
        }
        else if(keyHandler.isMoveLeft==true){
            x-=speed;
            direction = "left";
            isMove = true;
        }
        else if(keyHandler.isMoveRight==true){
            x+=speed;
            direction = "right";
            isMove = true;
        }
        else {
            isMove = false;
        }

        if(isMove){
            spriteIndex_timer++;
            if(spriteIndex_timer>10) {
                spriteIndex_timer = 0;
                spriteIndex++;
                if(spriteIndex >= 3) spriteIndex = 1;
            }
        } else spriteIndex = 1;
    }

    public void draw(Graphics2D g2){
        BufferedImage img = null;
        switch (direction) {
            case "up":
                if(spriteIndex == 1) img = up1;
                if(spriteIndex == 2) img = up2;
                break;
            case "down":
                if(spriteIndex == 1) img = down1;
                if(spriteIndex == 2) img = down2;
                break;
            case "left":
                if(spriteIndex == 1) img = left1;
                if(spriteIndex == 2) img = left2;
                break;
            case "right":
                if(spriteIndex == 1) img = right1;
                if(spriteIndex == 2) img = right2;
                break;
        }
        g2.drawImage(img, x,y, gamePanel.tile_size, gamePanel.tile_size, null);
        
    }
}
