import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // ===
    // Game values
    // ===
    final int original_tile_size = 32;
    final int scale = 2;

    final int tile_size = original_tile_size * scale;
    final int screen_col_tiles = 16;
    final int screen_row_tiles = 10;

    final int screen_height = screen_row_tiles * tile_size;
    final int screen_width = screen_col_tiles * tile_size;

    final int FPS = 60;

    // ===
    // Game system
    // ===
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    // ===
    // Game objects
    // ===
    int PlayerX = 2*tile_size;
    int PlayerY = 2*tile_size;
    int PlayerSpd = 10;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void runGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println("Game thread started");
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000/FPS;
        long prevTime = System.nanoTime();
        double delta = 0;
        long currTime = 0;

        long frameRate = 0;
        long timer = 0;

        while(gameThread != null){
            currTime = System.nanoTime();
            delta += (currTime-prevTime)/timePerFrame;
            timer += currTime-prevTime;
            prevTime = currTime;
            if(delta >=1 ){
                frameRate++;
                update();
                repaint();
                delta--;
            }
            if(timer >= 1000000000) {
                System.out.println(frameRate);
                timer = 0;
                frameRate = 0;
            }
        }
    }

    public void update(){
        if(keyHandler.isMoveDown==true){
            PlayerY+=PlayerSpd;
        }
        else if(keyHandler.isMoveUp==true){
            PlayerY-=PlayerSpd;
        }
        else if(keyHandler.isMoveLeft==true){
            PlayerX-=PlayerSpd;
        }
        else if(keyHandler.isMoveRight==true){
            PlayerX+=PlayerSpd;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(PlayerX,PlayerY, tile_size, tile_size);

        g2.dispose();
    }
}
