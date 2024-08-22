package main;
import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gp){
        this.gamePanel = gp;
    }

    public void check(Entity e){
        int leftX = e.worldX + e.hitbox.x;
        int rightX = leftX + e.hitbox.width;
        int topY = e.worldY + e.hitbox.y;
        int botY = topY + e.hitbox.height;

        int iColLeft = leftX / gamePanel.tile_size;
        int iColRight = rightX / gamePanel.tile_size;
        int iRowTop = topY / gamePanel.tile_size;
        int iRowBot = botY / gamePanel.tile_size;;
        int tile1, tile2;
        switch (e.direction) {
            case "up":
            iRowTop = (topY - e.speed) / gamePanel.tile_size;
            tile1 = gamePanel.tileManager.mapTile[iRowTop][iColLeft];
            tile2 = gamePanel.tileManager.mapTile[iRowTop][iColRight];
            if(gamePanel.tileManager.tile[tile1].collision
            || gamePanel.tileManager.tile[tile2].collision)
                e.collision = true;
            break;

            case "down":
            iRowBot = (botY + e.speed) / gamePanel.tile_size;
            tile1 = gamePanel.tileManager.mapTile[iRowBot][iColLeft];
            tile2 = gamePanel.tileManager.mapTile[iRowBot][iColRight];
            if(gamePanel.tileManager.tile[tile1].collision
            || gamePanel.tileManager.tile[tile2].collision)
                e.collision = true;
            break;

            case "left":
            iColLeft = (leftX - e.speed) / gamePanel.tile_size;
            tile1 = gamePanel.tileManager.mapTile[iRowBot][iColLeft];
            tile2 = gamePanel.tileManager.mapTile[iRowTop][iColLeft];
            if(gamePanel.tileManager.tile[tile1].collision
            || gamePanel.tileManager.tile[tile2].collision)
                e.collision = true;
            break;

            case "right":
            iColRight = (rightX + e.speed) / gamePanel.tile_size;
            tile1 = gamePanel.tileManager.mapTile[iRowBot][iColRight];
            tile2 = gamePanel.tileManager.mapTile[iRowTop][iColRight];
            if(gamePanel.tileManager.tile[tile1].collision
            || gamePanel.tileManager.tile[tile2].collision)
                e.collision = true;
            break;
        }

    }
}
