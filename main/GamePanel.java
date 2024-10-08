package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import objects.OBJECT;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    // ===
    // Game values
    // ===
    public final int original_tile_size = 32;
    public final int scale = 3;

    public final int tile_size = original_tile_size * scale;
    public final int screen_col_tiles = 12;
    public final int screen_row_tiles = 8;

    public final int screen_height = screen_row_tiles * tile_size;
    public final int screen_width = screen_col_tiles * tile_size;

    public final int FPS = 60;

    // ===
    // World values
    // ===

    public int world_col_tiles = 50;
    public int world_row_tiles = 50;

    // ===
    // Game system
    // ===
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    // ===
    // Game objects
    // ===
    public Player player = new Player(this, keyHandler);
    public TileManager tileManager = new TileManager(this);
    public CollisionChecker cCollsion = new CollisionChecker(this);

    public OBJECT[] objects = new OBJECT[16];
    public AssetSetter aSetter = new AssetSetter(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setDoubleBuffered(true);

    }

    public void setupGame(){
        aSetter.setObjects();
    }

    public void runGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println("Game thread started");
    }

    @Override
    public void run() {
        player.setDefaultValues();
        player.getPlayerImages();

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
        player.update();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);

        tileManager.draw(g2);

        for(int i=0;i<objects.length;i++){
            
            if(objects[i] != null){
                objects[i].draw(g2, this);
            }
        }

        player.draw(g2);
    }
}
