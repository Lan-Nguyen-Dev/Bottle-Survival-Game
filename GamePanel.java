import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int original_tile_size = 32;
    final int scale = 2;

    final int tile_size = original_tile_size * scale;
    final int screen_col_tiles = 16;
    final int screen_row_tiles = 10;

    final int screen_height = screen_row_tiles * tile_size;
    final int screen_width = screen_col_tiles * tile_size;

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
    }

    public void runGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null){

            update();
        
            draw();
        }
    }

    public void update(){

    }

    public void draw(){
        
    }
}
