package Tile;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    public TileManager(GamePanel gp){
        this.gamePanel = gp;

        tile = new Tile[10];

        getTileImages();
    }

    public void getTileImages(){
        try {
            for(int i=0;i<10;i++) tile[i] = new Tile();

            tile[0].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/grass.png"));
            tile[1].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/water.png"));
            tile[2].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall.png"));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void draw(Graphics2D g2){
        for(int i=0; i<gamePanel.screen_row_tiles;i++){
            for(int j=0;j<gamePanel.screen_col_tiles;j++){
                g2.drawImage(tile[2].img, j*gamePanel.tile_size, i*gamePanel.tile_size, gamePanel.tile_size, gamePanel.tile_size, null);
            }
        }
    }
}
