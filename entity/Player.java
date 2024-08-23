package entity;

import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage up1_pants, up2_pants, down1_pants, down2_pants, left1_pants, left2_pants, right1_pants, right2_pants;
    
    boolean isMove = false;
    int keys = 0;
    boolean hasPants = false;

    int spriteIndex = 1;
    int spriteIndex_timer = 0;

    public Player(GamePanel gp, KeyHandler kh){
        this.gamePanel = gp;
        this.keyHandler = kh;
        hitbox = new Rectangle();
    }

    public void setDefaultValues(){
        worldX = 9*gamePanel.tile_size + gamePanel.tile_size/2;
        worldY = 8*gamePanel.tile_size + gamePanel.tile_size/2;
        speed = 4;

        hitbox.x = -12*gamePanel.scale;
        hitbox.y = 4*gamePanel.scale;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        hitbox.width = 24*gamePanel.scale;
        hitbox.height = 11*gamePanel.scale;

        collision = false;
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

            up1_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-up-1.png"));
            up2_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-up-2.png"));
            down1_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-down-1.png"));
            down2_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-down-2.png"));
            left1_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-left-1.png"));
            left2_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-left-2.png"));
            right1_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-right-1.png"));
            right2_pants = ImageIO.read(getClass().getResourceAsStream("../res/player/hasPants/player-right-2.png"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(){
        if(keyHandler.isMoveDown || keyHandler.isMoveUp || keyHandler.isMoveLeft || keyHandler.isMoveRight){
            if(keyHandler.isMoveDown==true){
                direction = "down";
            }
            else if(keyHandler.isMoveUp==true){
                direction = "up";
            }
            else if(keyHandler.isMoveLeft==true){
                direction = "left";
            }
            else if(keyHandler.isMoveRight==true){
                direction = "right";
            }
            isMove = true;
            collision = false;

            gamePanel.cCollsion.check(this);

            int codeObject = gamePanel.cCollsion.checkObjects(this, true);

            if(codeObject!=404) {
                if(gamePanel.objects[codeObject].name == "key"){
                    keys += 1;
                    gamePanel.objects[codeObject] = null;
                }
                else if(gamePanel.objects[codeObject].name == "door"){
                    if(keys >= 1) {
                        keys -= 1;
                        gamePanel.objects[codeObject] = null;
                    }
                }
                else if(gamePanel.objects[codeObject].name == "pants"){
                    hasPants = true;
                    gamePanel.objects[codeObject] = null;
                }
            }
            
            if(collision == false){
                switch (direction) {
                    case "up":
                        worldY-=speed;
                        break;
                    case "down":
                        worldY+=speed;
                        break;
                    case "left":
                        worldX-=speed;
                        break;
                    case "right":
                        worldX+=speed;
                        break;
                }
            }
        }
        else isMove = false;

        if(isMove){
            spriteIndex_timer++;
            if(spriteIndex_timer>10) {
                spriteIndex_timer = 0;
                spriteIndex++;
                if(spriteIndex >= 3) spriteIndex = 1;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage img = null;
        switch (direction) {
            case "up":
                if(spriteIndex == 1) img = up1;
                if(spriteIndex == 2) img = up2;

                if(hasPants) {
                    if(spriteIndex == 1) img = up1_pants;
                    if(spriteIndex == 2) img = up2_pants;
                }
                break;
            case "down":
                if(spriteIndex == 1) img = down1;
                if(spriteIndex == 2) img = down2;

                if(hasPants) {
                    if(spriteIndex == 1) img = down1_pants;
                    if(spriteIndex == 2) img = down2_pants;
                }
                break;
            case "left":
                if(spriteIndex == 1) img = left1;
                if(spriteIndex == 2) img = left2;

                if(hasPants) {
                    if(spriteIndex == 1) img = left1_pants;
                    if(spriteIndex == 2) img = left2_pants;
                }
                break;
            case "right":
                if(spriteIndex == 1) img = right1;
                if(spriteIndex == 2) img = right2;

                if(hasPants) {
                    if(spriteIndex == 1) img = right1_pants;
                    if(spriteIndex == 2) img = right2_pants;
                }
                break;
        }
        g2.drawImage(img, 
        gamePanel.screen_width/2 - gamePanel.tile_size/2,
        gamePanel.screen_height/2 - gamePanel.tile_size/2, 
        gamePanel.tile_size, gamePanel.tile_size, null);

        // g2.setColor(Color.white);
        // g2.fillRect(gamePanel.screen_width/2 + hitbox.x, gamePanel.screen_height/2 + hitbox.y, hitbox.width, hitbox.height);
        // g2.dispose();
    }
}
