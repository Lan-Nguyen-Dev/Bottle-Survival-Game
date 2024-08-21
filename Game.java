import java.awt.Color;

import javax.swing.JFrame;

public class Game {
    final int original_tile_size = 32;
    final int scale = 2;

    final int tile_size = original_tile_size * scale;
    final int screen_col_tiles = 16;
    final int screen_row_tiles = 10;

    final int screen_height = screen_row_tiles * tile_size;
    final int screen_width = screen_col_tiles * tile_size;

    String name = "Bottle Survival";
    String version = "0.0";

    JFrame _window;

    public Game(){
        createWindow();
        _window.setVisible(true);
    }

    private void createWindow(){
        _window = new JFrame(name +" ver."+ version);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.getContentPane().setBackground(Color.BLACK);
        _window.setSize(screen_width, screen_height);
    }
}
