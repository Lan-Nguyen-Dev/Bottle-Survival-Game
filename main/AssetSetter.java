package main;

import objects.Obj_chest;
import objects.Obj_door;
import objects.Obj_key;
import objects.Obj_pants;

public class AssetSetter {
    GamePanel gamePanel;

    AssetSetter(GamePanel gp){
        this.gamePanel = gp;
    }

    public void setObjects(){
        gamePanel.objects[0] = new Obj_key();
        gamePanel.objects[0].worldX =9*gamePanel.tile_size;
        gamePanel.objects[0].worldY =9*gamePanel.tile_size;

        gamePanel.objects[1] = new Obj_door();
        gamePanel.objects[1].worldX = 12*gamePanel.tile_size;
        gamePanel.objects[1].worldY = 9*gamePanel.tile_size;

        // gamePanel.objects[2] = new Obj_chest();
        // gamePanel.objects[2].worldX = 16*gamePanel.tile_size;
        // gamePanel.objects[2].worldY = 9*gamePanel.tile_size;

        gamePanel.objects[3] = new Obj_pants();
        gamePanel.objects[3].worldX = 18*gamePanel.tile_size;
        gamePanel.objects[3].worldY = 9*gamePanel.tile_size;
    }
}
