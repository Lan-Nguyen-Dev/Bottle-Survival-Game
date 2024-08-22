package tile;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] mapTile;

    public TileManager(GamePanel gp){
        this.gamePanel = gp;

        tile = new Tile[10];
        mapTile = new int[gamePanel.screen_row_tiles][ gamePanel.screen_col_tiles];
        loadMap();
        getTileImages();
    }

    public void getTileImages(){
        try {
            for(int i=0;i<10;i++) tile[i] = new Tile();

            tile[0].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/grass.png"));
            tile[1].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/water.png"));
            tile[2].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall.png"));
            tile[3].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/tree.png"));
            tile[4].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/dirt.png"));
            tile[5].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/chest.png"));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void draw(Graphics2D g2){
        for(int i=0; i<gamePanel.screen_row_tiles;i++){
            for(int j=0;j<gamePanel.screen_col_tiles;j++){
                g2.drawImage(tile[mapTile[i][j]].img, j*gamePanel.tile_size, i*gamePanel.tile_size, gamePanel.tile_size, gamePanel.tile_size, null);
            }
        }
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("../maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));         
            
            for(int i=0; i<gamePanel.screen_row_tiles;i++){
                String temp = br.readLine();
                String[] numbers = temp.split(" ");
                for(int j=0;j<gamePanel.screen_col_tiles;j++){
                    int num = Integer.parseInt(numbers[j]);
                    mapTile[i][j] = num;
                }
            }

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
