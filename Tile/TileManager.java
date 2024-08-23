package tile;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTile;

    public TileManager(GamePanel gp){
        this.gamePanel = gp;

        tile = new Tile[10];
        mapTile = new int[gamePanel.world_row_tiles][ gamePanel.world_col_tiles];
        
        getTileImages();

        loadMap("../maps/world1.txt");
    }

    public void getTileImages(){
        try {
            for(int i=0;i<10;i++) tile[i] = new Tile();

            tile[0].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/grass.png"));
            
            tile[1].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/water.png"));
            tile[1].collision = true;

            tile[2].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall.png"));
            tile[2].collision = true;

            tile[3].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/tree.png"));
            tile[3].collision = true;

            tile[4].img = ImageIO.read(getClass().getResourceAsStream("../res/tiles/dirt.png"));

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void draw(Graphics2D g2){
        int playerWX = gamePanel.player.worldX;
        int playerWY = gamePanel.player.worldY;

        int screenOX = playerWX - gamePanel.screen_width/2;
        int screenOY = playerWY - gamePanel.screen_height/2;

        for(int i=0; i<gamePanel.world_row_tiles;i++){
            for(int j=0;j<gamePanel.world_col_tiles;j++){
                int tileWX = j*gamePanel.tile_size;
                int tileWY = i*gamePanel.tile_size;

                int tileSX = tileWX - screenOX;
                int tileSY = tileWY - screenOY;


                if(tileWX < screenOX - gamePanel.tile_size 
                || tileWY < screenOY - gamePanel.tile_size
                || tileWX > screenOX + gamePanel.screen_width
                || tileWY > screenOY + gamePanel.screen_height) continue;

                g2.drawImage(
                    tile[mapTile[i][j]].img,
                    (tileSX),
                    (tileSY),
                    gamePanel.tile_size,
                    gamePanel.tile_size,
                    null);
            }
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));         
            
            for(int i=0; i<gamePanel.world_row_tiles;i++){
                String temp = br.readLine();
                String[] numbers = temp.split(" ");
                for(int j=0;j<gamePanel.world_col_tiles;j++){
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
