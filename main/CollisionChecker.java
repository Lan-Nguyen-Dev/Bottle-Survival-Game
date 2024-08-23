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

    public int checkObjects(Entity e, boolean isPlayer){
        int index = 404;

        for(int i=0;i<gamePanel.objects.length;i++){
            if( gamePanel.objects[i]==null) continue;
            e.hitbox.x += e.worldX;
            e.hitbox.y += e.worldY;

            gamePanel.objects[i].solidArea.x += gamePanel.objects[i].worldX;
            gamePanel.objects[i].solidArea.y += gamePanel.objects[i].worldY;

            switch (e.direction) {
                case "up":
                    e.hitbox.y -= e.speed;
                    break;
                case "down":
                    e.hitbox.y += e.speed;
                    break;
                case "left":
                    e.hitbox.x -= e.speed;
                    break;
                case "right":
                    e.hitbox.x += e.speed;
                    break;
            }

            if(e.hitbox.intersects(gamePanel.objects[i].solidArea)){
                index = i;
                if(gamePanel.objects[i].collision) e.collision = true;
            }

            gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
            gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;

            e.hitbox.x = e.hitboxDefaultX;
            e.hitbox.y = e.hitboxDefaultY;
        }
        return index;
    }
}
