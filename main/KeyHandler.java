package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean isMoveUp, isMoveDown, isMoveLeft, isMoveRight;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            isMoveUp = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            isMoveDown = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            isMoveLeft = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            isMoveRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            isMoveUp = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            isMoveDown = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            isMoveLeft = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            isMoveRight = false;
        }
    }

}
