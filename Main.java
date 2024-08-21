import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        String name = "Bottle Survival";
        String version = "0.0";

        GamePanel gamePanel = new GamePanel();

        JFrame frame = new JFrame(name + " ver " + version);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);

        gamePanel.runGameThread();
    }

}